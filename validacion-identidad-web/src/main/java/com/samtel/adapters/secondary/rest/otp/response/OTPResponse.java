package com.samtel.adapters.secondary.rest.otp.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OTPResponse {
    private String codResultadoOTP;
    private String idTransaccionOTP;
}
