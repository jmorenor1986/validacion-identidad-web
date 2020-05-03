package co.com.santander.adapters.primary.rest.validarotp;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.primary.rest.common.dto.Response;
import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
import co.com.santander.adapters.primary.rest.validarotp.input.OtpInput;
import co.com.santander.adapters.primary.rest.validarotp.mapper.OtpConverterPayloadToDto;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.ports.primary.ValidarOTPService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public ResponseEntity<Response> validarOTP(@RequestBody GeneralPayload<OtpInput> otpPayload) throws JSONException {
        Optional<ResponseDTO> result = validarOTPService.validarOTP(otpConverterPayloadToDto.requestToDto(otpPayload));
        if (result.isPresent()) {
            return new ResponseEntity<>(modelMapper.map(result.get(), Response.class), HttpStatus.OK);
        }
        throw new BusinessException("Error al consultar los datos", new Throwable("valores no retornados"), "0");
    }
}
