package co.com.santander.adapters.secondary.rest.clients;

import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.secondary.rest.otp.payload.OtpPayload;
import co.com.santander.core.dto.ResponseDTO;
import co.com.santander.core.validator.ValidarEstado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${evidente-master.service-name-otp}", url = "${evidente-master.url}")
public interface OtpCliente {

    @ValidarEstado
    @RequestMapping(method = RequestMethod.POST, value = "/iniciartransotp")
    ResponseEntity<ResponseDTO> iniciarTransaccion(GeneralPayload<OtpPayload> payload);

    @ValidarEstado
    @RequestMapping(method = RequestMethod.POST, value = "/generarotp")
    ResponseEntity<ResponseDTO> generarOTP(GeneralPayload<OtpPayload> payload);

    @ValidarEstado
    @RequestMapping(method = RequestMethod.POST, value = "/verificarotp")
    ResponseEntity<ResponseDTO> verificarOTP(GeneralPayload<OtpPayload> payload);

}
