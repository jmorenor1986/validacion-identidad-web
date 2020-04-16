package co.com.santander.adapters.secondary.rest.identidad.payload;

import com.fasterxml.jackson.annotation.JsonRootName;
import co.com.santander.adapters.secondary.rest.common.payload.SolucionPayload;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName(value = "requestBody")
public class ClientePayload {
    private String primerApellido;
    private String nombres;
    private FechaExpedicionPayload fechaExpedicion;
    private SolucionPayload solucion;

}
