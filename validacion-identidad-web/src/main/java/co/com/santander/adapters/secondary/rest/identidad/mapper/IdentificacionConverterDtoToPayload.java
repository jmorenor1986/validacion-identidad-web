package co.com.santander.adapters.secondary.rest.identidad.mapper;

import co.com.santander.adapters.common.mapper.ConverterDtoToRequest;
import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.ClientePayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.FechaExpedicionPayload;
import co.com.santander.adapters.secondary.rest.common.payload.SolucionPayload;
import co.com.santander.core.dto.ClienteDTO;
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
