package com.example.msbook.service;

import com.example.msbook.entity.Libro;
import com.example.msbook.entity.Provedores;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> listar();
    Libro guardar(Libro libro);
    Libro editar(Libro libro, Integer id);
    void eliminar(Integer id);
    Optional<Libro> listarPorId(Integer id);
}
