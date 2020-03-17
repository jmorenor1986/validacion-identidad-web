package com.samtel.core.services.impl;

import com.samtel.core.dto.CuestionarioDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.ports.primary.VerificarPreguntasService;
import com.samtel.ports.secondary.rest.IdentidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificarPreguntasServiceImpl implements VerificarPreguntasService {

    private final IdentidadService identidadService;

    @Autowired
    public VerificarPreguntasServiceImpl(IdentidadService identidadService) {
        this.identidadService = identidadService;
    }

    @Override
    public Optional<ResponseDTO> verificarPreguntas(CuestionarioDTO cuestionarioDTO) {
        return Optional.empty();
    }
}
