package com.biblioteca.universidad.repository;

import com.biblioteca.universidad.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByUsername(String username);    
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}       