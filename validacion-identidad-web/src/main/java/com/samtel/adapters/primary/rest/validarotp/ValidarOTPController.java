package com.samtel.adapters.primary.rest.validarotp;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.primary.rest.dto.Response;
import com.samtel.adapters.primary.rest.validarotp.input.OtpInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/validacion/otp")
public class ValidarOTPController {

    @PostMapping("/")
    public ResponseEntity<Response> validarOTP(GeneralPayload<OtpInput> otpPayload) {
        return null;
    }
}
