package com.samtel.adapters.secondary.rest.clients;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.common.payload.RequestHeader;
import com.samtel.adapters.secondary.rest.identidad.payload.ClientePayload;
import com.samtel.adapters.secondary.rest.identidad.payload.FechaExpedicionPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.SolucionPayload;

public class MockRequest {

    public static GeneralPayload<ClientePayload> clientePayload() {
        GeneralPayload<ClientePayload> payload = new GeneralPayload<>();
        RequestHeader requestHeader = RequestHeader.builder()
                .codAliado("001")
                .usuarioAliado("pepito.perez@colsanitas.com.co")
                .sesionId("132456786543")
                .identificacion("00080036190")
                .ipOrigen("172.0.0.1")
                .numeroSolicitudCredito("00012")
                .tipoIdentificacion("1")
                .build();
        ClientePayload clientePayload = ClientePayload.builder()
                .fechaExpedicion(FechaExpedicionPayload.builder()
                        .timestamp("986947200120")
                        .build())
                .nombres("Freddy")
                .primerApellido("Forero")
                .solucion(SolucionPayload.builder()
                        .codSapSolucion("")
                        .nitEntidadExterna("")
                        .build())
                .build();
        payload.setRequestBody(clientePayload);
        payload.setRequestHeader(requestHeader);
        return payload;
    }
}
