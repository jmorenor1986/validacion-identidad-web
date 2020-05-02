package co.com.santander.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
    private String codRespuesta;
    private Object respuestaServicio;
    private String mensajeError;
}
