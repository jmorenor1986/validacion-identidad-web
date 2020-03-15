package com.samtel.adapters.secondary.rest.otp.payload;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.samtel.adapters.secondary.rest.common.payload.SolucionPayload;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName(value = "requestBody")
public class OtpPayload {
    private String codigoValidacion;
    private DatosCuestionarioPayload datosCuestionario;
    private SolucionPayload solucion;
}
