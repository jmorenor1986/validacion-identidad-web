package com.samtel.core.services.impl;

import com.samtel.core.dto.CuestionarioDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.core.errors.GenericError;
import com.samtel.core.errors.ResourceNotResponse;
import com.samtel.ports.primary.VerificarPreguntasService;
import com.samtel.ports.secondary.rest.IdentidadService;
import io.vavr.control.Either;
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
        Either<GenericError, ResponseDTO> verificarPreguntasReto = verificarPreguntasReto(cuestionarioDTO);
        if (verificarPreguntasReto.get().getRespuestaServicio().toString().isEmpty())
            return setResponseDTO("null", "0");
        return setResponseDTO("null", "1");

    }

    private Either<GenericError, ResponseDTO> verificarPreguntasReto(CuestionarioDTO cuestionarioDTO) {
        return identidadService.validarPreguntasReto(cuestionarioDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse(("El servicio no responde"))));
    }

    private Optional<ResponseDTO> setResponseDTO(Object response, String codResult) {
        return Optional.of(ResponseDTO.builder()
                .codRespuesta(codResult)
                .respuestaServicio(response)
                .build());
    }
}
