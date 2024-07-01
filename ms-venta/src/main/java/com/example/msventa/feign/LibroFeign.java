package com.example.msventa.feign;

import com.example.msventa.dto.LibroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="ms-book-service")
public interface LibroFeign {
    @GetMapping("/libros/{id}")
    ResponseEntity<LibroDto>listarLibro(@PathVariable(required = true) Integer id);
    @PutMapping("/libros/{id}/stock")
    ResponseEntity<Void> actualizarStock(@PathVariable Integer id, @RequestParam Integer cantidad);

}
