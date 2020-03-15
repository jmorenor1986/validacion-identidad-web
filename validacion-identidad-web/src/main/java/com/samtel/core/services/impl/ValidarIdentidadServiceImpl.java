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
        return Optional.of(responseValidacion.get());
    }

    private Either<GenericError, ResponseDTO> clienteValidacion(ClienteDTO clienteDTO) throws JSONException {
        return identidadService.validarIdentidad(clienteDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Either<GenericError, ResponseDTO> obtenerPreguntasReto(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO) {
        return identidadService.obtenerPreguntasReto(datosBasicosDTO, datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Either<GenericError, ResponseDTO> clienteIniciarTransaccion(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO) {
        return otpService.iniciarTransaccion(datosBasicosDTO, datosAdicionalesDTO.getRegValidacion())
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Either<GenericError, ResponseDTO> clienteGenerarOTP(DatosBasicosDTO datosBasicosDTO, DatosAdicionalesDTO datosAdicionalesDTO) {
        return otpService.generarOTP(datosBasicosDTO, datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }


}
