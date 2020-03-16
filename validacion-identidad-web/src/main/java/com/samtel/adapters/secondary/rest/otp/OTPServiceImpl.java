package com.samtel.adapters.secondary.rest.otp;

import com.samtel.adapters.common.utilities.JsonUtilities;
import com.samtel.adapters.secondary.rest.clients.OtpCliente;
import com.samtel.adapters.secondary.rest.otp.mapper.OtpConverterDtoToPayload;
import com.samtel.core.dto.DatosAdicionalesDTO;
import com.samtel.core.dto.ResponseDTO;
import com.samtel.ports.secondary.rest.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OTPServiceImpl implements OTPService {
    private final OtpCliente otpCliente;
    private final OtpConverterDtoToPayload otpConverterDtoToPayload;
    private final JsonUtilities jsonUtilities;

    @Autowired
    public OTPServiceImpl(OtpCliente otpCliente, OtpConverterDtoToPayload otpConverterDtoToPayload, JsonUtilities jsonUtilities) {
        this.otpCliente = otpCliente;
        this.otpConverterDtoToPayload = otpConverterDtoToPayload;
        this.jsonUtilities = jsonUtilities;
    }

    @Override
    public Optional<ResponseDTO> generarOTP(DatosAdicionalesDTO datosAdicionalesDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ResponseDTO> iniciarTransaccion(DatosAdicionalesDTO datosAdicionalesDTO) {
        ResponseEntity<ResponseDTO> result = otpCliente.iniciarTransaccion(otpConverterDtoToPayload.dtoToRequest(datosAdicionalesDTO));
        if (result.getStatusCodeValue() == 200) {
            ResponseDTO responseDTO = result.getBody();
            responseDTO.setRespuestaServicio(jsonUtilities.getValueForGivenKey("RespValidacion", "codResultadoOTP", (String) responseDTO.getRespuestaServicio()));
            return Optional.of(responseDTO);
        } else
            return Optional.of((ResponseDTO.builder()
                    .codRespuesta("" + result.getStatusCodeValue())
                    .respuestaServicio(null)
                    .resultError("Error llamando al servicio ")
                    .build()));
    }

}
