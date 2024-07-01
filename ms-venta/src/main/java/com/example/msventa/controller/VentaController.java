package com.example.msventa.controller;

import com.example.msventa.entity.Venta;
import com.example.msventa.service.VentaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping("/realizar")
    public ResponseEntity<Venta> realizarVenta(@RequestHeader("Authorization") String token) {
        Venta nuevaVenta = ventaService.realizarVenta(token);
        return ResponseEntity.ok(nuevaVenta);
    }
}