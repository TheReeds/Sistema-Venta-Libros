package com.example.msventa.dto;

import jakarta.persistence.*;

public class LibroDto {

    private Integer id;
    private String titulo;
    private String autor;
    private Integer stock;

    private CategoriaDto categoria;

    private ProvedoresDto provedores;
}
