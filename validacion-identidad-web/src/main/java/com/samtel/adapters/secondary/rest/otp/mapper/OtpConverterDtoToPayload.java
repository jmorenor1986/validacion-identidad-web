package com.samtel.adapters.secondary.rest.otp.mapper;

import com.samtel.adapters.common.mapper.ConverterDtoToRequest;
import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.common.properties.OtpProperties;
import com.samtel.adapters.secondary.rest.common.payload.SolucionPayload;
import com.samtel.adapters.secondary.rest.otp.payload.DatosCuestionarioPayload;
import com.samtel.adapters.secondary.rest.otp.payload.OtpPayload;
import com.samtel.core.dto.DatosAdicionalesDTO;
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
                .build();
        result.setRequestHeader(setHeader(request.getDatosBasicosDTO()));
        result.setRequestBody(payload);
        return result;
    }
}
