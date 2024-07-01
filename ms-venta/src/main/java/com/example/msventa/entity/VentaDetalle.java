package com.example.msventa.entity;

import com.example.msventa.dto.LibroDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double cantidad;
    private Double precio;
    private Integer libroId;

    public VentaDetalle() {
        this.cantidad = (double) 0;
        this.precio = (double) 0;
    }
    @Transient
    LibroDto libroDto;
}
