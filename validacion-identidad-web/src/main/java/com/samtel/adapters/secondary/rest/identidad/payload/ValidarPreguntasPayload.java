package com.samtel.adapters.secondary.rest.identidad.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.samtel.adapters.secondary.rest.common.payload.SolucionPayload;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "requestBody")
public class ValidarPreguntasPayload {
    private String idCuestionario;
    private String regCuestionario;
    private RespuestaPayload respuesta;
    private SolucionPayload solucion;
}
