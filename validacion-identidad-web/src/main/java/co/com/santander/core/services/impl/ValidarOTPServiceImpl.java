package co.com.santander.core.services.impl;

import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.errors.GenericError;
import co.com.santander.core.errors.ResourceNotResponse;
import co.com.santander.ports.primary.ValidarOTPService;
import co.com.santander.ports.secondary.rest.IdentidadService;
import co.com.santander.ports.secondary.rest.OTPService;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidarOTPServiceImpl implements ValidarOTPService {
    private final OTPService otpService;
    private final IdentidadService identidadService;

    @Autowired
    public ValidarOTPServiceImpl(OTPService otpService, IdentidadService identidadService) {
        this.otpService = otpService;
        this.identidadService = identidadService;
    }

    @Override
    public Optional<ResponseDTO> validarOTP(DatosAdicionalesDTO datosAdicionalesDTO) {
        //invocar cliente validarOTP
        Either<GenericError, ResponseDTO> verificarOTP = clienteVerificarOTP(datosAdicionalesDTO);
        //TODO validar que respuesta debe traer
        if (verificarOTP.get().getRespuestaServicio().toString().length() > 0)
            return setResponseDTO(null, "1");
        else
            return invocaPreguntasReto(datosAdicionalesDTO.getDatosBasicosDTO(), datosAdicionalesDTO.getRegValidacion());
    }

    private Either<GenericError, ResponseDTO> clienteVerificarOTP(DatosAdicionalesDTO datosAdicionalesDTO) {
        return otpService.generarOTP(datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Optional<ResponseDTO> invocaPreguntasReto(DatosBasicosDTO datosBasicosDTO, String regValidacion) {
        Either<GenericError, ResponseDTO> obtenerPreguntasReto = obtenerPreguntasReto(DatosAdicionalesDTO.builder()
                .regValidacion(regValidacion)
                .datosBasicosDTO(datosBasicosDTO)
                .build());
        return setResponseDTO(obtenerPreguntasReto.get().getRespuestaServicio(), "2");
    }

    private Either<GenericError, ResponseDTO> obtenerPreguntasReto(DatosAdicionalesDTO datosAdicionalesDTO) {
        return identidadService.obtenerPreguntasReto(datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Optional<ResponseDTO> setResponseDTO(Object response, String codResult) {
        return Optional.of(ResponseDTO.builder()
                .codRespuesta(codResult)
                .respuestaServicio(response)
                .build());
    }
}
