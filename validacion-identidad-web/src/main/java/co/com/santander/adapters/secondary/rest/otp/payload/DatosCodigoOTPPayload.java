package co.com.santander.adapters.secondary.rest.otp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosCodigoOTPPayload {
    private String idTransaccionOTP;
    private String codigoOTP;
}
