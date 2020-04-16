package co.com.santander.adapters.secondary.rest.identidad.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaPayload {
    private String idPregunta;
    private String idRespuesta;
}
