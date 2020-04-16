package co.com.santander.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CuestionarioDTO {
    private String idCuestionario;
    private String regCuestionario;
    private RespuestaCuestionarioDTO respuesta;
    private DatosBasicosDTO datosBasicosDTO;
}
