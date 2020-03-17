package com.samtel.adapters.primary.rest.validarotp.mapper;

import com.samtel.adapters.common.mapper.ConverterRequestToDto;
import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.common.payload.RequestHeader;
import com.samtel.adapters.primary.rest.validarotp.input.OtpInput;
import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.DatosBasicosDTO;
import org.springframework.stereotype.Service;

@Service
public class OtpConverterPayloadToDto extends ConverterRequestToDto<OtpInput, DatosAdicionalesDTO> {
    @Override
    public DatosAdicionalesDTO requestToDto(GeneralPayload<OtpInput> payload) {
        return DatosAdicionalesDTO.builder()
                .idTransaccionOTP(payload.getRequestBody().getIdTransaccionOTP())
                .regValidacion(payload.getRequestBody().getRegValidacion())
                .codigoOTP(payload.getRequestBody().getCodigoOTP())
                .datosBasicosDTO(setDatosBasicosDTO(payload.getRequestHeader()))
                .build();
    }

    private DatosBasicosDTO setDatosBasicosDTO(RequestHeader requestHeader) {
        return DatosBasicosDTO.builder()
                .codAliado(requestHeader.getCodAliado())
                .identificacion(requestHeader.getIdentificacion())
                .ipOrigen(requestHeader.getIpOrigen())
                .numeroSolicitudCredito(requestHeader.getNumeroSolicitudCredito())
                .sesionId(requestHeader.getSesionId())
                .tipoIdentificacion(requestHeader.getTipoIdentificacion())
                .usuarioAliado(requestHeader.getUsuarioAliado())
                .build();
    }
}
