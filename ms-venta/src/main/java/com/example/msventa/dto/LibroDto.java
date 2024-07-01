package com.example.msventa.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class LibroDto {
    private Integer id;
    private String titulo;
    private String autor;
    private Integer stock;
    private CategoriaDto categoria;
    private ProvedoresDto provedores;
    private Double precio;
}
