package co.com.santander.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CuestionarioDTO {
    private String idCuestionario;
    private String regCuestionario;
    private List<RespuestaCuestionarioDTO> respuestas;
    private DatosBasicosDTO datosBasicosDTO;
}
