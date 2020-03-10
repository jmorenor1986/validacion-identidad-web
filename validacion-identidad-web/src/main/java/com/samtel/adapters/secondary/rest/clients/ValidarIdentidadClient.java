package com.samtel.adapters.secondary.rest.clients;

import com.samtel.adapters.secondary.rest.common.GeneralPayload;
import com.samtel.adapters.secondary.rest.identidad.payload.ClientePayload;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "validarIdentidad", url = "${servicios.baseUri}")
public interface ValidarIdentidadClient {
    @RequestMapping(method = RequestMethod.POST, value = "${servicios.urlValidarIdentidad}")
    String validarIdentidad(GeneralPayload<ClientePayload> request);

    @RequestMapping(method = RequestMethod.POST, value = "${servicios.urlIniciarTransaccion}")
    String iniciarTransaccion(GeneralPayload<ClientePayload> request);

    @RequestMapping(method = RequestMethod.POST, value = "${servicios.obtenerPreguntasReto}")
    String obtenerPreguntasReto(GeneralPayload<ClientePayload> request);


}
