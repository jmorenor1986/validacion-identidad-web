package co.com.santander.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OTPDTO {
    private String codResultadoOTP;
    private String idTransaccionOTP;
}
