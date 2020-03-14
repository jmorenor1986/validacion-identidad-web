package com.samtel.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosAdicionalesDTO {
    private String regValidacion;
    private String idTransaccionOTP;
}
