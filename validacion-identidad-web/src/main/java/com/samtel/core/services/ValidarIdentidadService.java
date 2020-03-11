package com.samtel.core.services;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;

import java.util.Optional;

public interface ValidarIdentidadService {

    Optional<ResponseDTO> validar(ClienteDTO clienteDTO);
}