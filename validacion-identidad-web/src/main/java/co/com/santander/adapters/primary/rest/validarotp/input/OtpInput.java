package co.com.santander.adapters.primary.rest.validarotp.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OtpInput {
    private String regValidacion;
    private String codigoOTP;
    private String idTransaccionOTP;
}
