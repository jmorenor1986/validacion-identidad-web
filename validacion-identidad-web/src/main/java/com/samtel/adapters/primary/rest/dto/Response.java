package com.samtel.adapters.primary.rest.dto;

import lombok.Data;

@Data
public class Response {
    private String codRespuesta;
    private Object respuestaServicio;
    private String resultError;
}
