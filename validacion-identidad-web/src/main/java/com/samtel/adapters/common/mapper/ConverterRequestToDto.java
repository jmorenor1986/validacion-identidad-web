package com.samtel.adapters.common.mapper;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.common.payload.RequestHeader;
import com.samtel.core.dto.DatosBasicosDTO;

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
