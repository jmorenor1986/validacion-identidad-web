package com.samtel.ports.primary;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;

import java.util.Optional;

public interface ValidarIdentidadService {

    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO);

}
