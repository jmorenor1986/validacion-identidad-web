package co.com.santander.adapters.secondary.rest.identidad.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaPayload {
    private String idPregunta;
    private String idRespuesta;
}
