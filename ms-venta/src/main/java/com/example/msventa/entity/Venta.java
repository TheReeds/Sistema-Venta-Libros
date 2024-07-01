package com.example.msventa.entity;

import com.example.msventa.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<Carrito> items;
}
