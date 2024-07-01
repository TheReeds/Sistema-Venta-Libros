package com.example.msventa.service.impl;

import com.example.msventa.entity.Venta;
import com.example.msventa.feign.AuthFeign;
import com.example.msventa.feign.CustomerFeign;
import com.example.msventa.feign.LibroFeign;
import com.example.msventa.repository.VentaRepository;
import com.example.msventa.service.VentaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    private LibroFeign libroFeign;

    @Autowired
    private AuthFeign authFeign;

    @Transactional
    public Venta realizarVenta(Venta venta, String token) {
        // Obtener el userId desde el token
        ResponseEntity<Integer> response = authFeign.getUserId(token);
        if (response.getStatusCode().is2xxSuccessful()) {
            Integer userId = response.getBody();
            venta.getCustomer().setId(userId);

            // Aquí puedes agregar la lógica para actualizar el stock de los libros y cualquier otra lógica de negocio

            return ventaRepository.save(venta);
        } else {
            throw new RuntimeException("No se pudo obtener el userId del token");
        }
    }

}
