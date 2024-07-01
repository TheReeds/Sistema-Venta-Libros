package com.example.msbook.controller;

import com.example.msbook.entity.Libro;
import com.example.msbook.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listar();
    }

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    @DeleteMapping("/{id}")
    public String eliminarLibro(@PathVariable Integer id) {
        libroService.eliminar(id);
        return "Libro eliminado correctamente";
    }

    @PostMapping("/{id}")
    public Libro editarLibro(@RequestBody Libro libro, @PathVariable Integer id) {
        return libroService.editar(libro, id);
    }
    @GetMapping("/{id}")
    public Optional<Libro> listarlibroId(@PathVariable Integer id) {
        return libroService.listarPorId(id);
    }
}
