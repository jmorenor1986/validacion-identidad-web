package com.samtel.adapters.common.mapper;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.common.payload.RequestHeader;
import com.samtel.core.dto.DatosBasicosDTO;

public abstract class ConverterDtoToRequest<T, R> {

    public abstract GeneralPayload<T> dtoToRequest(R request);

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
