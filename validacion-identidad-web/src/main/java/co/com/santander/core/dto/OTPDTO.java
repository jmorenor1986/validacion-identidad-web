package co.com.santander.core.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OTPDTO {
    private String codResultadoOTP;
    private String idTransaccionOTP;
}