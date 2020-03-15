package com.samtel.adapters.secondary.rest.identidad.mapper;

import com.samtel.adapters.common.mapper.ConverterDtoToRequest;
import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.ClientePayload;
import com.samtel.adapters.secondary.rest.identidad.payload.FechaExpedicionPayload;
import com.samtel.adapters.secondary.rest.common.payload.SolucionPayload;
import com.samtel.core.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public class IdentificacionConverterDtoToPayload extends ConverterDtoToRequest<ClientePayload, ClienteDTO> {
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
