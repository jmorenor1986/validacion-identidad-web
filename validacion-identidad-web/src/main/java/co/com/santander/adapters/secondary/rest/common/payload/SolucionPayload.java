package co.com.santander.adapters.secondary.rest.common.payload;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName(value = "solucion")
public class SolucionPayload {
    private String codSapSolucion;
    private String nitEntidadExterna;
}
