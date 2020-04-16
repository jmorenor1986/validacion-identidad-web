package co.com.santander.adapters.common.mapper;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.common.payload.RequestHeader;
import co.com.santander.core.dto.DatosBasicosDTO;

public abstract class ConverterRequestToDto<T, R> {

    public abstract R requestToDto(GeneralPayload<T> payload);

    public DatosBasicosDTO setDatosBasicos(RequestHeader requestHeader) {
        return DatosBasicosDTO.builder()
                .usuarioAliado(requestHeader.getUsuarioAliado())
                .tipoIdentificacion(requestHeader.getTipoIdentificacion())
                .sesionId(requestHeader.getSesionId())
                .numeroSolicitudCredito(requestHeader.getNumeroSolicitudCredito())
                .ipOrigen(requestHeader.getIpOrigen())
                .identificacion(requestHeader.getIdentificacion())
                .codAliado(requestHeader.getCodAliado())
                .build();
    }
}
