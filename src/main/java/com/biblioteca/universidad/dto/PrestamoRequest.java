package com.biblioteca.universidad.dto;

import lombok.Data;

@Data
public class PrestamoRequest {
    private Long libroId;
    private Long usuarioId;
}