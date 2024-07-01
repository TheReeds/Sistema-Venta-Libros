package com.example.msbook.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String autor;
    private Integer stock;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "provedores_id", nullable = false)
    private Provedores provedores;
}
