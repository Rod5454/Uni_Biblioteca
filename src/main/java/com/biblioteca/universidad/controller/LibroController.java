package com.biblioteca.universidad.controller;

import com.biblioteca.universidad.entity.Libro;
import com.biblioteca.universidad.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listar() {   
        return libroService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.guardarLibro(libro));
    }
}