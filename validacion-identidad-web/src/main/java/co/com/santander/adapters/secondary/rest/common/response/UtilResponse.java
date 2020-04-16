package co.com.santander.adapters.secondary.rest.common.response;

import co.com.santander.core.dto.ResponseDTO;

public class UtilResponse {
    public static ResponseDTO setResponse(String error, String code, Object response) {
        return ResponseDTO.builder()
                .resultError(error)
                .codRespuesta(code)
                .respuestaServicio(response)
                .build();
    }
}
