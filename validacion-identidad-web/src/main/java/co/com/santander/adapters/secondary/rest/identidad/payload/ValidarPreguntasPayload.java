package co.com.santander.adapters.secondary.rest.identidad.payload;

import co.com.santander.adapters.secondary.rest.common.payload.SolucionPayload;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "requestBody")
public class ValidarPreguntasPayload {
    private String idCuestionario;
    private String regCuestionario;
    private List<RespuestaPayload> respuestas;
    private SolucionPayload solucion;
}
