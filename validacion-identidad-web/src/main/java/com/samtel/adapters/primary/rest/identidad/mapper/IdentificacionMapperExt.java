package com.samtel.adapters.primary.rest.identidad.mapper;

import com.samtel.adapters.common.mapper.RestRequestMapper;
import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.ClientePayload;
import com.samtel.adapters.secondary.rest.identidad.payload.FechaExpedicionPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.SolucionPayload;
import com.samtel.core.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public class IdentificacionMapperExt extends RestRequestMapper<ClientePayload, ClienteDTO> {
    @Override
    public GeneralPayload<ClientePayload> dtoToRequest(ClienteDTO request) {
        GeneralPayload<ClientePayload> result = new GeneralPayload<>();
        ClientePayload clientePayload = ClientePayload.builder()
                .solucion(SolucionPayload.builder()
                        .codSapSolucion("")
                        .nitEntidadExterna("")
                        .build())
                .primerApellido(request.getPrimerApellido())
                .nombres(request.getPrimerNombre().concat(" ").concat(request.getSegundoNombre()))
                .fechaExpedicion(FechaExpedicionPayload.builder()
                        .timestamp(request.getFechaExpedicion())
                        .build())
                .build();
        result.setRequestBody(clientePayload);
        result.setRequestHeader(setHeader(request.getDatosBasicosDTO()));
        return result;
    }
}
