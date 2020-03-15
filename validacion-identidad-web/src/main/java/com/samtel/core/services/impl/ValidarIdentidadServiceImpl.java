package com.samtel.core.services.impl;

import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.DatosBasicosDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.core.errors.GenericError;
import com.samtel.core.errors.ResourceNotResponse;
import com.samtel.ports.primary.ValidarIdentidadService;
import com.samtel.ports.secondary.rest.IdentidadService;
import com.samtel.ports.secondary.rest.OTPService;
import io.vavr.control.Either;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidarIdentidadServiceImpl implements ValidarIdentidadService {
    private final IdentidadService identidadService;
    private final OTPService otpService;

    @Autowired
    public ValidarIdentidadServiceImpl(IdentidadService identidadService, OTPService otpService) {
        this.identidadService = identidadService;
        this.otpService = otpService;
    }

    @Override
    public Optional<ResponseDTO> validar(ClienteDTO clienteDTO) throws JSONException {
        Either<GenericError, ResponseDTO> responseValidacion = clienteValidacion(clienteDTO);
        DatosAdicionalesDTO datosAdicionalesDTO = DatosAdicionalesDTO.builder()
                .regValidacion(responseValidacion.get().getRespuestaServicio().toString())
                .datosBasicosDTO(clienteDTO.getDatosBasicosDTO())
                .build();
        if (responseValidacion.get().getCodRespuesta().equalsIgnoreCase("1") || responseValidacion.get().getCodRespuesta().equalsIgnoreCase("3")) {
            Either<GenericError, ResponseDTO> responseIniciarTransaccion = clienteIniciarTransaccion(datosAdicionalesDTO);
            if (responseIniciarTransaccion.get().getRespuestaServicio().toString().equalsIgnoreCase("99")) {
                Either<GenericError, ResponseDTO> preguntasReto = obtenerPreguntasReto(DatosAdicionalesDTO.builder()
                        .regValidacion(responseValidacion.get().getRespuestaServicio().toString())
                        .datosBasicosDTO(clienteDTO.getDatosBasicosDTO())
                        .build());
            }
        }
        return null;
    }

    private Either<GenericError, ResponseDTO> clienteValidacion(ClienteDTO clienteDTO) throws JSONException {
        return identidadService.validarIdentidad(clienteDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Either<GenericError, ResponseDTO> obtenerPreguntasReto(DatosAdicionalesDTO datosAdicionalesDTO) {
        return identidadService.obtenerPreguntasReto(datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Either<GenericError, ResponseDTO> clienteIniciarTransaccion(DatosAdicionalesDTO datosAdicionalesDTO) {
        return otpService.iniciarTransaccion(datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Either<GenericError, ResponseDTO> clienteGenerarOTP(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO) {
        return otpService.generarOTP(datosBasicosDTO, datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }
}
