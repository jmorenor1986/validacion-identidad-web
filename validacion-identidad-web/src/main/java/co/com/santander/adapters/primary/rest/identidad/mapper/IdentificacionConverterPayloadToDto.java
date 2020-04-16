package co.com.santander.adapters.primary.rest.identidad.mapper;

import co.com.santander.adapters.common.mapper.ConverterRequestToDto;
import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.primary.rest.identidad.input.ClienteInput;
import co.com.santander.core.dto.ClienteDTO;
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
