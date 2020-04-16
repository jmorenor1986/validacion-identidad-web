package co.com.santander.adapters.common.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestHeader {
    private String codAliado;
    private String usuarioAliado;
    private String sesionId;
    private String ipOrigen;
    private String numeroSolicitudCredito;
    private String tipoIdentificacion;
    private String identificacion;
}
