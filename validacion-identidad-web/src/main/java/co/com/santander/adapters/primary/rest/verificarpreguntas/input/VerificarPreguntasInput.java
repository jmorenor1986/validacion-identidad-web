package co.com.santander.adapters.primary.rest.verificarpreguntas.input;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VerificarPreguntasInput {
    private String idCuestionario;
    private String regCuestionario;
    private List<RespuestaInput> respuestas;
}
