package co.com.santander.adapters.primary.rest.verificarpreguntas.input;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaInput {
    private String idPregunta;
    private String idRespuesta;
}
