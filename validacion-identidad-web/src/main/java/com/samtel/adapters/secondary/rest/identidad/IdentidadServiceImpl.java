package com.samtel.adapters.secondary.rest.identidad;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.DatosBasicosDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.ports.secondary.rest.IdentidadService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentidadServiceImpl implements IdentidadService {
    @Override
    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ResponseDTO> obtenerPreguntasReto(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO) {
        return Optional.empty();
    }
}
