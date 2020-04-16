package co.com.santander.adapters.primary.rest.validarotp.mapper;

import co.com.santander.adapters.common.mapper.ConverterRequestToDto;
import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.common.payload.RequestHeader;
import co.com.santander.adapters.primary.rest.validarotp.input.OtpInput;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import co.com.santander.core.dto.DatosBasicosDTO;
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
