package com.example.msventa.feign;

import com.example.msventa.dto.LibroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-book-service")
public interface LibroFeign {
    @GetMapping("/{id}")
    ResponseEntity<LibroDto>listarLibro(@PathVariable(required = true) Integer id);

}
