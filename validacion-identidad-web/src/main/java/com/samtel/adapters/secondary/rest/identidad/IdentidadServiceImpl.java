package com.samtel.adapters.secondary.rest.identidad;

import com.samtel.adapters.common.utilities.JsonUtilities;
import com.samtel.adapters.secondary.rest.clients.IdentificacionCliente;
import com.samtel.adapters.secondary.rest.identidad.mapper.IdentificacionConverterDtoToPayload;
import com.samtel.core.dto.ClienteDTO;
import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.ResponseDTO;
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

    @Autowired
    public IdentidadServiceImpl(IdentificacionCliente identificacionCliente, IdentificacionConverterDtoToPayload identificacionMapperExt, JsonUtilities jsonUtilities) {
        this.identificacionCliente = identificacionCliente;
        this.identificacionMapperExt = identificacionMapperExt;
        this.jsonUtilities = jsonUtilities;
    }


    @Override
    public Optional<ResponseDTO> validarIdentidad(ClienteDTO clienteDTO) throws JSONException {
        ResponseEntity<ResponseDTO> result = identificacionCliente.validacionIdentidad(identificacionMapperExt.dtoToRequest(clienteDTO));
        if (result.getStatusCodeValue() == 200) {
            ResponseDTO responseDTO = result.getBody();
            responseDTO.setRespuestaServicio(jsonUtilities.getValueForGivenKey("RespValidacion", "regValidacion", (String) responseDTO.getRespuestaServicio()));
            return Optional.of(responseDTO);
        } else
            return Optional.of((ResponseDTO.builder()
                    .codRespuesta("" + result.getStatusCodeValue())
                    .respuestaServicio(null)
                    .resultError("Error llamando al servicio ")
                    .build()));


    }

    @Override
    public Optional<ResponseDTO> obtenerPreguntasReto(DatosAdicionalesDTO datosAdicionalesDTO) {
        return Optional.empty();
    }
}
