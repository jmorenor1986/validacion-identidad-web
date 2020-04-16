package co.com.santander.adapters.primary.rest.validarotp;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.primary.rest.dto.Response;
import co.com.santander.adapters.primary.rest.validarotp.input.OtpInput;
import co.com.santander.adapters.primary.rest.validarotp.mapper.OtpConverterPayloadToDto;
import co.com.santander.ports.primary.ValidarOTPService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<Response> validarOTP(@RequestBody GeneralPayload<OtpInput> otpPayload) {
        Response response = modelMapper
                .map(validarOTPService.validarOTP(otpConverterPayloadToDto
                        .requestToDto(otpPayload)), Response.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
