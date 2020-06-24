package co.com.santander.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosAdicionalesDTO {
    private String regValidacion;
    private String idTransaccionOTP;
    private String numeroCelular;
    private String codigoOTP;
    private DatosBasicosDTO datosBasicosDTO;
}
