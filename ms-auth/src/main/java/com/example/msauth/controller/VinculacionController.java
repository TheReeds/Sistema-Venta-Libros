package com.example.msauth.controller;

import com.example.msauth.entity.AuthUser;
import com.example.msauth.service.AuthUserService;
import com.example.msauth.service.VinculacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class VinculacionController {
    @Autowired
    private VinculacionService vinculacionService;

    @PostMapping("/vincularCliente/{userId}/{clienteId}")
    public ResponseEntity<AuthUser> vincularCliente(@PathVariable Integer userId, @PathVariable Integer clienteId) {
        AuthUser authUser = vinculacionService.vincularCliente(userId, clienteId);
        if (authUser == null) {
            return ResponseEntity.badRequest().build(); // Usuario o Cliente no encontrado
        }
        return ResponseEntity.ok(authUser);
    }

    @PostMapping("/vincularVendedor/{userId}/{vendedorId}")
    public ResponseEntity<AuthUser> vincularVendedor(@PathVariable Integer userId, @PathVariable Integer vendedorId) {
        AuthUser authUser = vinculacionService.vincularVendedor(userId, vendedorId);
        if (authUser == null) {
            return ResponseEntity.badRequest().build(); // Usuario o Vendedor no encontrado
        }
        return ResponseEntity.ok(authUser);
    }
}
