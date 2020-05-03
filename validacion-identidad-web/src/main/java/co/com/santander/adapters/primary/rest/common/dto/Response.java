package co.com.santander.adapters.primary.rest.common.dto;

import lombok.Data;

@Data
public class Response {
    private String codRespuesta;
    private Object respuestaServicio;
    private String mensajeError;
}
