package co.com.santander.core.services.impl;

import co.com.santander.core.dto.CuestionarioDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.errors.GenericError;
import co.com.santander.core.errors.ResourceNotResponse;
import co.com.santander.ports.primary.VerificarPreguntasService;
import co.com.santander.ports.secondary.rest.IdentidadService;
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
