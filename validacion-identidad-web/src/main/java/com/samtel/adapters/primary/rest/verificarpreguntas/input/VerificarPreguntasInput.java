package com.samtel.adapters.primary.rest.verificarpreguntas.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificarPreguntasInput {
    private String idCuestionario;
    private String regCuestionario;
    private RespuestaInput respuesta;
}
