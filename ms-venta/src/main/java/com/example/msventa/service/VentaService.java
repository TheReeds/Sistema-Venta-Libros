package com.example.msventa.service;

import com.example.msventa.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    Venta realizarVenta(String token);
}