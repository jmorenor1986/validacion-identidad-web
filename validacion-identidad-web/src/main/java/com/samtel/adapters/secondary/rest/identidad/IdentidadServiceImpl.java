package com.samtel.adapters.secondary.rest.identidad;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.common.payload.RequestHeader;
import com.samtel.adapters.common.utilities.JsonUtilities;
import com.samtel.adapters.secondary.rest.clients.IdentificacionCliente;
import com.samtel.adapters.secondary.rest.common.payload.SolucionPayload;
import com.samtel.adapters.secondary.rest.common.response.UtilResponse;
import com.samtel.adapters.secondary.rest.identidad.mapper.CuestionarioConverterDtoToPayload;
import com.samtel.adapters.secondary.rest.identidad.mapper.IdentificacionConverterDtoToPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.PreguntasPayload;
import com.samtel.core.dto.*;
import com.samtel.ports.secondary.rest.IdentidadService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IdentidadServiceImpl implements IdentidadService {
    private final IdentificacionCliente identificacionCliente;
    private final IdentificacionConverterDtoToPayload identificacionMapperExt;
    private final JsonUtilities jsonUtilities;
    private final CuestionarioConverterDtoToPayload cuestionarioConverterDtoToPayload;

    @Autowired
    public IdentidadServiceImpl(IdentificacionCliente identificacionCliente, IdentificacionConverterDtoToPayload identificacionMapperExt, JsonUtilities jsonUtilities, CuestionarioConverterDtoToPayload cuestionarioConverterDtoToPayload) {
        this.identificacionCliente = identificacionCliente;
        this.identificacionMapperExt = identificacionMapperExt;
        this.jsonUtilities = jsonUtilities;
        this.cuestionarioConverterDtoToPayload = cuestionarioConverterDtoToPayload;
    }


    @Override
    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO) throws JSONException {

        ResponseEntity<ResponseDTO> result = identificacionCliente.validacionIdentidad(identificacionMapperExt.dtoToRequest(clienteDTO));

        if (result.getStatusCodeValue() == 200) {
            ResponseDTO responseDTO = result.getBody();
            responseDTO.setRespuestaServicio(jsonUtilities.getValueForGivenKey("RespValidacion", "regValidacion", (String) responseDTO.getRespuestaServicio()));
            return Optional.of(responseDTO);
        } else
            return Optional.of(UtilResponse.setResponse("Error llamando al servicio validar identidad", "" + result.getStatusCodeValue(), null));
    }

    @Override
    public Optional<ResponseDTO> obtenerPreguntasReto(DatosAdicionalesDTO datosAdicionalesDTO) {
        ResponseEntity<ResponseDTO> result = identificacionCliente.generarPreguntas(setPayLoad(datosAdicionalesDTO));
        if (result.getStatusCodeValue() == 200) {
            return Optional.of(result.getBody());
        } else
            return Optional.of(UtilResponse.setResponse("Error al llamar el servicio obtener preguntas reto", "" + result.getStatusCodeValue(), null));

    }

    @Override
    public Optional<ResponseDTO> validarPreguntasReto(CuestionarioDTO cuestionarioDTO) {
        ResponseEntity<ResponseDTO> result = identificacionCliente.validarPreguntas(cuestionarioConverterDtoToPayload.dtoToRequest(cuestionarioDTO));
        if (result.getStatusCodeValue() == 200)
            return Optional.of(result.getBody());
        else
            return Optional.of(UtilResponse.setResponse("Error al llamar el servicio validación preguntas reto", "" + result.getStatusCodeValue(), null));
    }

    private GeneralPayload<PreguntasPayload> setPayLoad(DatosAdicionalesDTO datosAdicionalesDTO) {
        GeneralPayload<PreguntasPayload> payload = new GeneralPayload<>();
        payload.setRequestHeader(setHeader(datosAdicionalesDTO.getDatosBasicosDTO()));
        payload.setRequestBody(PreguntasPayload.builder()
                .regValidacion(datosAdicionalesDTO.getRegValidacion())
                .solucion(SolucionPayload.builder()
                        .nitEntidadExterna("")
                        .codSapSolucion("")
                        .build())
                .build());
        return payload;
    }

    //TODO mapper Preguntas
    public RequestHeader setHeader(DatosBasicosDTO datosBasicosDTO) {
        return RequestHeader.builder()
                .identificacion(datosBasicosDTO.getIdentificacion())
                .tipoIdentificacion(datosBasicosDTO.getTipoIdentificacion())
                .numeroSolicitudCredito(datosBasicosDTO.getNumeroSolicitudCredito())
                .ipOrigen(datosBasicosDTO.getIpOrigen())
                .sesionId(datosBasicosDTO.getSesionId())
                .usuarioAliado(datosBasicosDTO.getUsuarioAliado())
                .codAliado(datosBasicosDTO.getCodAliado())
                .build();
    }

}
