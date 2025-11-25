package com.biblioteca.universidad.service;

import com.biblioteca.universidad.dto.AuthResponse;
import com.biblioteca.universidad.dto.LoginRequest;
import com.biblioteca.universidad.dto.RegisterRequest;
import com.biblioteca.universidad.entity.Rol;
import com.biblioteca.universidad.entity.Usuario;
import com.biblioteca.universidad.repository.RolRepository;
import com.biblioteca.universidad.repository.UsuarioRepository;
import com.biblioteca.universidad.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String registrarUsuario(RegisterRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        Rol rolUsuario = rolRepository.findByNombre("ROLE_USUARIO")
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        
        usuario.setRoles(new HashSet<>(Collections.singletonList(rolUsuario)));

        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(token);
    }
}