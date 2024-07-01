package com.example.msventa.feign;

import com.example.msventa.dto.CustomerDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer-service",path = "/customer")
public interface CustomerFeign {

    @GetMapping("/{id}")  // Debes especificar la ruta completa del endpoint del servicio de clientes
    @CircuitBreaker(name = "customerListarPorIdCB", fallbackMethod = "fallbackCustomerPorId")
    public ResponseEntity<CustomerDto>buscarPorId(@PathVariable(required = true) CustomerDto id);
    default ResponseEntity<CustomerDto> fallbackCustomerPorId(Integer id, Exception e) {
        return ResponseEntity.ok(new CustomerDto());
    }
}

