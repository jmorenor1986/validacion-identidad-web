package co.com.santander.adapters.secondary.rest.otp.mapper;

import co.com.santander.adapters.common.mapper.ConverterDtoToRequest;
import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.common.properties.OtpProperties;
import co.com.santander.adapters.secondary.rest.common.payload.SolucionPayload;
import co.com.santander.adapters.secondary.rest.otp.payload.DatosCodigoOTPPayload;
import co.com.santander.adapters.secondary.rest.otp.payload.DatosCuestionarioPayload;
import co.com.santander.adapters.secondary.rest.otp.payload.DatosReconocerPayload;
import co.com.santander.adapters.secondary.rest.otp.payload.OtpPayload;
import co.com.santander.core.dto.DatosAdicionalesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpConverterDtoToPayload extends ConverterDtoToRequest<OtpPayload, DatosAdicionalesDTO> {

    private final OtpProperties otpProperties;

    @Autowired
    public OtpConverterDtoToPayload(OtpProperties otpProperties) {
        this.otpProperties = otpProperties;
    }

    @Override
    public GeneralPayload<OtpPayload> dtoToRequest(DatosAdicionalesDTO request) {
        GeneralPayload<OtpPayload> result = new GeneralPayload<>();
        OtpPayload payload = OtpPayload.builder()
                .codigoValidacion(otpProperties.getCodigoValidacion())
                .datosCuestionario(DatosCuestionarioPayload.builder()
                        .regValidacion(request.getRegValidacion())
                        .build())
                .solucion(SolucionPayload.builder()
                        .codSapSolucion("")
                        .nitEntidadExterna("")
                        .build())
                .datosCodigoOTP(DatosCodigoOTPPayload.builder()
                        .idTransaccionOTP(request.getIdTransaccionOTP())
                        .codigoOTP(request.getCodigoOTP())
                        .build())
                .datosReconocer(DatosReconocerPayload.builder()
                        .numeroCelular(request.getNumeroCelular())
                        .build())
                .build();
        result.setRequestHeader(setHeader(request.getDatosBasicosDTO()));
        result.setRequestBody(payload);
        return result;
    }
}
