package com.biblioteca.universidad.controller;

import com.biblioteca.universidad.dto.PrestamoRequest;
import com.biblioteca.universidad.entity.Prestamo;
import com.biblioteca.universidad.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<Prestamo> crear(@RequestBody PrestamoRequest request) {
        return ResponseEntity.ok(prestamoService.registrarPrestamo(request));
    }

    @GetMapping("/mis-prestamos")
    public List<Prestamo> misPrestamos() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        return prestamoService.listarPorUsername(username);
    }
    
    @PutMapping("/{id}/devolucion")
    public ResponseEntity<Prestamo> devolverLibro(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.devolverLibro(id));
    }
}

