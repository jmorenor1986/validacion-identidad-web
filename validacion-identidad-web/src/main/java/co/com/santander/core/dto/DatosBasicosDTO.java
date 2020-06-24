package co.com.santander.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatosBasicosDTO {
    private String codAliado;
    private String usuarioAliado;
    private String sesionId;
    private String ipOrigen;
    private String numeroSolicitudCredito;
    private String tipoIdentificacion;
    private String identificacion;
}
