package com.samtel.adapters.secondary.rest.clients;


import com.samtel.adapters.common.GeneralPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.ClientePayload;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "identificacion", url = "${servicios.validacionIdentidad}")
public interface IdentificacionCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/")
    ResponseEntity<?> validacionIdentidad(GeneralPayload<ClientePayload> payload);
}
