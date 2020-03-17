package com.samtel.adapters.primary.rest.validarotp;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.primary.rest.dto.Response;
import com.samtel.adapters.primary.rest.validarotp.input.OtpInput;
import com.samtel.ports.primary.ValidarOTPService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/otp")
public class ValidarOTPController {

    private final ValidarOTPService validarOTPService;
    private final ModelMapper modelMapper;
    private final OtpConverterPayloadToDto otpConverterPayloadToDto;

    @Autowired
    public ValidarOTPController(ValidarOTPService validarOTPService, ModelMapper modelMapper, OtpConverterPayloadToDto otpConverterPayloadToDto) {
        this.validarOTPService = validarOTPService;
        this.modelMapper = modelMapper;
        this.otpConverterPayloadToDto = otpConverterPayloadToDto;
    }

    @PostMapping("/")
    public ResponseEntity<Response> validarOTP(GeneralPayload<OtpInput> otpPayload) {
        Response response = modelMapper
                .map(validarOTPService.validarOTP(otpConverterPayloadToDto
                        .requestToDto(otpPayload)), Response.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
