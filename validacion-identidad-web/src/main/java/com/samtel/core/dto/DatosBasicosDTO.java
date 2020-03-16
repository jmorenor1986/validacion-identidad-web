package com.samtel.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatosBasicosDTO {
    private String codAliado;
    private String usuarioAliado;
    private String sesionId;
    private String ipOrigen;
    private String numeroSolicitudCredito;
    private String tipoIdentificacion;
    private String identificacion;
}
