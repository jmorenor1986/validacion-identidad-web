package com.samtel.ports.primary;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.ResponseDTO;
import org.json.JSONException;

import java.util.Optional;

public interface ValidarIdentidadService {

    Optional<ResponseDTO> validar(ClienteDTO clienteDTO) throws JSONException;
}
