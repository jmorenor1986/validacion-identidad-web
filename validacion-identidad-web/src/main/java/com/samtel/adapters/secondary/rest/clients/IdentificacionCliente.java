package com.samtel.adapters.secondary.rest.clients;


import com.samtel.adapters.common.payload.GeneralPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.ClientePayload;
import com.samtel.adapters.secondary.rest.identidad.payload.PreguntasPayload;
import com.samtel.core.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "identificacion", url = "${servicios.validacionIdentidad}")
public interface IdentificacionCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/validar")
    ResponseEntity<ResponseDTO> validacionIdentidad(GeneralPayload<ClientePayload> payload);

    @RequestMapping(method = RequestMethod.POST, value = "/preguntas")
    ResponseEntity<ResponseDTO> generarPreguntas(GeneralPayload<PreguntasPayload> payload);
}
