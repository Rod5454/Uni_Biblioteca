package com.biblioteca.universidad.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "libro_id", nullable = false)
    private Libro libro;
    
    @PrePersist
    public void prePersist() {
        this.fechaPrestamo = LocalDate.now();
        this.estado = "ACTIVO";
    }
}