package com.samtel.adapters.secondary.rest.identidad.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName(value = "fechaExpedicion")
public class FechaExpedicionPayload {
    private String timestamp;
}
