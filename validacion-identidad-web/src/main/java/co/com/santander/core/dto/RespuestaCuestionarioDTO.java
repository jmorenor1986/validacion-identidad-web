package co.com.santander.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaCuestionarioDTO {
    private String idPregunta;
    private String idRespuesta;

}
