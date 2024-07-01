package com.example.msventa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private Integer userId;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<VentaDetalle> detalles;
}
