package co.com.santander.core.services.impl;

import co.com.santander.core.dto.*;
import co.com.santander.core.errors.GenericError;
import co.com.santander.core.errors.ResourceNotResponse;
import co.com.santander.ports.primary.ValidarIdentidadService;
import co.com.santander.ports.secondary.rest.IdentidadService;
import co.com.santander.ports.secondary.rest.OTPService;
import io.vavr.control.Either;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        //Invocar cliente validacion
        Either<GenericError, ResponseDTO> responseValidacion = clienteValidacion(clienteDTO);
        if (!Objects.isNull(responseValidacion.get().getResultError()) && !responseValidacion.get().getResultError().isEmpty())
            return Optional.of(responseValidacion.get());
        DatosAdicionalesDTO datosAdicionalesDTO = DatosAdicionalesDTO.builder()
                .regValidacion(responseValidacion.get().getRespuestaServicio().toString())
                .datosBasicosDTO(clienteDTO.getDatosBasicosDTO())
                .numeroCelular(clienteDTO.getNumeroCelular())
                .build();
        //Si la respuesta es 1 o 5
        if (responseValidacion.get().getCodRespuesta().equalsIgnoreCase("1") || responseValidacion.get().getCodRespuesta().equalsIgnoreCase("5")) {
            //Invoca cliente iniciar transaccion
            Either<GenericError, ResponseDTO> responseIniciarTransaccion = clienteIniciarTransaccion(datosAdicionalesDTO);
            //Si codResultadoOTP es 99
            OTPDTO result = (OTPDTO) responseIniciarTransaccion.get().getRespuestaServicio();
            if (result.getCodResultadoOTP().equalsIgnoreCase("99")) {
                //retorno Cod respuesta 3 y el servicio de JSON preguntas
                return invocaPreguntasReto(clienteDTO.getDatosBasicosDTO(), responseValidacion.get().getRespuestaServicio().toString());
            }
            //Si codResultadoOTP no es 99
            else {
                //Invocar cliente generarOTP
                Either<GenericError, ResponseDTO> responseGenerarOTP = invocaGenerarOTP(clienteDTO.getDatosBasicosDTO(),
                        responseValidacion.get().getRespuestaServicio().toString(), result.getIdTransaccionOTP());
                //Si codResultadoOTP es 99
                if (responseGenerarOTP.get().getRespuestaServicio().toString().equalsIgnoreCase("99")) {
                    //Invocar preguntas reto
                    return invocaPreguntasReto(clienteDTO.getDatosBasicosDTO(), responseValidacion.get().getRespuestaServicio().toString());
                } else {
                    //CodRespuesta 2
                    return setResponseDTO(responseGenerarOTP.get().getRespuestaServicio(), "2");
                }
            }
        }//Si Cod respuesta no es 1 0 5 pero es 9
        //TODO ¿Por que las respuestas para esto es NULL?
        else if (responseValidacion.get().getCodRespuesta().equalsIgnoreCase("9"))
            return setResponseDTO(null, "0");
            //Si Cod respuesta no es 9
        else
            return setResponseDTO(null, "1");
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

    private Either<GenericError, ResponseDTO> clienteGenerarOTP(DatosAdicionalesDTO datosAdicionalesDTO) {
        return otpService.generarOTP(datosAdicionalesDTO)
                .map(Either::<GenericError, ResponseDTO>right)
                .orElse(Either.left(new ResourceNotResponse("El servicio no responde")));
    }

    private Optional<ResponseDTO> invocaPreguntasReto(DatosBasicosDTO datosBasicosDTO, String regValidacion) {
        Either<GenericError, ResponseDTO> obtenerPreguntasReto = obtenerPreguntasReto(DatosAdicionalesDTO.builder()
                .regValidacion(regValidacion)
                .datosBasicosDTO(datosBasicosDTO)
                .build());
        return setResponseDTO(obtenerPreguntasReto.get().getRespuestaServicio(), "3");
    }

    private Either<GenericError, ResponseDTO> invocaGenerarOTP(DatosBasicosDTO datosBasicosDTO, String regValidacion, String idTransaccionOTP) {
        return clienteGenerarOTP(DatosAdicionalesDTO.builder()
                .datosBasicosDTO(datosBasicosDTO)
                .regValidacion(regValidacion)
                .idTransaccionOTP(idTransaccionOTP)
                .build());
    }

    private Optional<ResponseDTO> setResponseDTO(Object response, String codResult) {
        return Optional.of(ResponseDTO.builder()
                .codRespuesta(codResult)
                .respuestaServicio(response)
                .build());
    }
}
