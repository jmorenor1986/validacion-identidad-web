package com.samtel.adapters.secondary.rest.common.response;

import com.samtel.core.dto.ResponseDTO;

public class UtilResponse {
    public static ResponseDTO setResponse(String error, String code, Object response) {
        return ResponseDTO.builder()
                .resultError(error)
                .codRespuesta(code)
                .respuestaServicio(response)
                .build();
    }
}
