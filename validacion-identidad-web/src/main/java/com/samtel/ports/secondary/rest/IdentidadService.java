package com.samtel.ports.secondary.rest;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;

import java.util.Optional;

public interface IdentidadService {

    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO);

    public Optional<ResponseDTO> iniciarTransaccion(ClienteDTO clienteDTO);

    public Optional<ResponseDTO> obtenerPreguntasReto(ClienteDTO clienteDTO);
}
