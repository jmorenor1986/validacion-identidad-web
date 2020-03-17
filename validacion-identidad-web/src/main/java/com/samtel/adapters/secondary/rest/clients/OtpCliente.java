package com.samtel.adapters.secondary.rest.clients;

import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.secondary.rest.otp.payload.OtpPayload;
import com.samtel.core.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "otp", url = "${servicios.otp}")
public interface OtpCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/iniciartransotp")
    ResponseEntity<ResponseDTO> iniciarTransaccion(GeneralPayload<OtpPayload> payload);

    @RequestMapping(method = RequestMethod.POST, value = "/generarotp")
    ResponseEntity<ResponseDTO> generarOTP(GeneralPayload<OtpPayload> payload);

    @RequestMapping(method = RequestMethod.POST, value = "/verificarotp")
    ResponseEntity<ResponseDTO> verificarOTP(GeneralPayload<OtpPayload> payload);

}
