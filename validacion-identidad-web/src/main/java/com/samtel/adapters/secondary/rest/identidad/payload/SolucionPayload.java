package com.samtel.adapters.secondary.rest.identidad.payload;

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
