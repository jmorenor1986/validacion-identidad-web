package co.com.santander.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaCuestionarioDTO {
    private String idPregunta;
    private String idRespuesta;

}