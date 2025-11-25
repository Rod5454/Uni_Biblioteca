package com.biblioteca.universidad.service;

import com.biblioteca.universidad.dto.PrestamoRequest;
import com.biblioteca.universidad.entity.Libro;
import com.biblioteca.universidad.entity.Prestamo;
import com.biblioteca.universidad.entity.Usuario;
import com.biblioteca.universidad.repository.LibroRepository;
import com.biblioteca.universidad.repository.PrestamoRepository;
import com.biblioteca.universidad.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Prestamo registrarPrestamo(PrestamoRequest request) {
        Libro libro = libroRepository.findById(request.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (libro.getStock() <= 0) {
            throw new RuntimeException("No hay stock disponible");
        }

        libro.setStock(libro.getStock() - 1);
        libroRepository.save(libro);

        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> listarPorUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return prestamoRepository.findByUsuarioId(usuario.getId());
    }
}