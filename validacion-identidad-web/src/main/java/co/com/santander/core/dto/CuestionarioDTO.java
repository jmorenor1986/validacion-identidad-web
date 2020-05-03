package co.com.santander.core.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CuestionarioDTO {
    private String idCuestionario;
    private String regCuestionario;
    private List<RespuestaCuestionarioDTO> respuestas;
    private DatosBasicosDTO datosBasicosDTO;
}
