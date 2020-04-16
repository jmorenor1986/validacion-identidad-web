package co.com.santander.adapters.secondary.rest.identidad.payload;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonRootName(value = "fechaExpedicion")
public class FechaExpedicionPayload {
    private String timestamp;
}
