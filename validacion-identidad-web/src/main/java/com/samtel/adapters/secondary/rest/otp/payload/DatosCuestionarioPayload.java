package com.samtel.adapters.secondary.rest.otp.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosCuestionarioPayload {
    private String regValidacion;
}
