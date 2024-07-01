package com.example.msventa.service.impl;

import com.example.msventa.dto.LibroDto;
import com.example.msventa.entity.CarritoItem;
import com.example.msventa.entity.Venta;
import com.example.msventa.entity.VentaDetalle;
import com.example.msventa.feign.AuthFeign;
import com.example.msventa.feign.LibroFeign;
import com.example.msventa.repository.CarritoItemRepository;
import com.example.msventa.repository.VentaRepository;
import com.example.msventa.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CarritoItemRepository carritoItemRepository;

    @Autowired
    private AuthFeign authFeign;
    @Autowired
    private LibroFeign libroFeign;

    @Override
    @Transactional
    public Venta realizarVenta(String token) {
        // Obtener el userId desde el token
        Integer userId = authFeign.getUserId(token).getBody();

        // Obtener los items del carrito del usuario
        List<CarritoItem> items = carritoItemRepository.findByUserId(userId);

        if (items.isEmpty()) {
            throw new RuntimeException("El carrito está vacío");
        }

        // Crear la venta
        Venta venta = new Venta();
        venta.setUserId(userId);
        venta.setFecha(new Date());
        venta = ventaRepository.save(venta);

        // Crear los detalles de la venta y actualizar el stock de los libros
        for (CarritoItem item : items) {
            // Obtener el libro desde ms-book-service
            ResponseEntity<LibroDto> libroResponse = libroFeign.listarLibro(item.getLibroId());
            if (libroResponse.getStatusCode().is2xxSuccessful()) {
                LibroDto libro = libroResponse.getBody();

                // Verificar si hay suficiente stock
                if (libro.getStock() < item.getCantidad()) {
                    throw new RuntimeException("No hay suficiente stock para el libro: " + libro.getTitulo());
                }

                // Crear y guardar el detalle de la venta
                VentaDetalle detalle = new VentaDetalle();
                detalle.setVenta(venta);
                detalle.setLibroId(libro.getId());
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecio(libro.getPrecio());
                venta.getDetalles().add(detalle);

                // Actualizar el stock del libro
                libroFeign.actualizarStock(libro.getId(), libro.getStock() - item.getCantidad());
            } else {
                throw new RuntimeException("No se pudo obtener información del libro con ID: " + item.getLibroId());
            }
        }

        // Guardar la venta con los detalles
        venta = ventaRepository.save(venta);

        // Vaciar el carrito
        carritoItemRepository.deleteAll(items);

        return venta;
    }

}
