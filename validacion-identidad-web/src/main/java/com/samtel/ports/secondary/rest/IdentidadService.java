package com.samtel.ports.secondary.rest;

import com.samtel.core.dto.ClienteDTO;

import java.util.Optional;

public interface IdentidadService {

    public Optional<String> validarIdentidad(ClienteDTO clienteDTO);

    public Optional<String> iniciarTransaccion(ClienteDTO clienteDTO);

    public Optional<String> obtenerPreguntasReto(ClienteDTO clienteDTO);
}
