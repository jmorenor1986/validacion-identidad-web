package co.com.santander.adapters.common.mapper;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.common.payload.RequestHeader;
import co.com.santander.core.dto.DatosBasicosDTO;

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
