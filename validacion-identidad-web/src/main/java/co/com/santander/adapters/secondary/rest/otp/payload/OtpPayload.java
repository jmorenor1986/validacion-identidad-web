package co.com.santander.adapters.secondary.rest.otp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import co.com.santander.adapters.secondary.rest.common.payload.SolucionPayload;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "requestBody")
public class OtpPayload {
    private String codigoValidacion;
    private DatosCuestionarioPayload datosCuestionario;
    private SolucionPayload solucion;
    private DatosReconocerPayload datosReconocer;
    private DatosCodigoOTPPayload datosCodigoOTP;
}
