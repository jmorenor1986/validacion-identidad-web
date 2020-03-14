package com.samtel.ports.secondary.rest;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.DatosBasicosDTO;
import com.samtel.core.dto.ResponseDTO;
import org.json.JSONException;

import java.util.Optional;

public interface IdentidadService {

    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO) throws JSONException;

    public Optional<ResponseDTO> obtenerPreguntasReto(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO);
}
