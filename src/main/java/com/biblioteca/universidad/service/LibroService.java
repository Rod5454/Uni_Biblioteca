package com.biblioteca.universidad.service;

import com.biblioteca.universidad.entity.Libro;
import com.biblioteca.universidad.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }
    
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }
    
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}