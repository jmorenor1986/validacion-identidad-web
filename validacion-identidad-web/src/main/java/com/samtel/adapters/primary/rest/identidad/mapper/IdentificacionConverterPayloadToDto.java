package com.samtel.adapters.primary.rest.identidad.mapper;

import com.samtel.adapters.common.mapper.ConverterRequestToDto;
import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.primary.rest.identidad.payload.ClienteInput;
import com.samtel.core.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public class IdentificacionConverterPayloadToDto extends ConverterRequestToDto<ClienteInput, ClienteDTO> {

    @Override
    public ClienteDTO requestToDto(GeneralPayload<ClienteInput> payload) {
        return ClienteDTO.builder()
                .numeroCelular(payload.getRequestBody().getNumeroCelular())
                .primerApellido(payload.getRequestBody().getPrimerApellido())
                .segundoApellido(payload.getRequestBody().getSegundoApellido())
                .primerNombre(payload.getRequestBody().getPrimerNombre())
                .segundoNombre(payload.getRequestBody().getSegundoNombre())
                .fechaExpedicion(payload.getRequestBody().getFechaExpedicion())
                .datosBasicosDTO(setDatosBasicos(payload.getRequestHeader()))
                .build();
    }
}
