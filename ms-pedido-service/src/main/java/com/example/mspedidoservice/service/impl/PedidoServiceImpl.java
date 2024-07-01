package com.example.mspedidoservice.service.impl;

import com.example.mspedidoservice.dto.ClienteDto;
import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.entity.PedidoDetalle;
import com.example.mspedidoservice.feign.ClienteFeign;
import com.example.mspedidoservice.feign.ProductoFeign;
import com.example.mspedidoservice.repository.PedidoRepository;
import com.example.mspedidoservice.service.PedidoService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRespository;
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private ProductoFeign productoFeign;
    @Override
    public List<Pedido> listar() {
        return pedidoRespository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRespository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Integer id) {
        Pedido pedido = pedidoRespository.findById(id).get();
        pedido.setClienteDto(clienteFeign.busacarPorId(pedido.getClienteId()).getBody());
        /*for (PedidoDetalle pedidoDetalle: pedido.getDetalle()){
            pedidoDetalle.setProductoDto(productoFeign.buscarPOrId(pedidoDetalle.getProductoId()).getBody());
        }*/
        List<PedidoDetalle>pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
            pedidoDetalle.setProductoDto(productoFeign.buscarPorId(pedidoDetalle.getProductoId()).getBody());
            return pedidoDetalle;
        }).toList();
        pedido.setDetalle(pedidoDetalles);
        return pedidoRespository.findById(id);
    }

    @Override
    public Pedido actualizar(Pedido pedido) {
        return pedidoRespository.save(pedido);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRespository.deleteById(id);

    }
}
