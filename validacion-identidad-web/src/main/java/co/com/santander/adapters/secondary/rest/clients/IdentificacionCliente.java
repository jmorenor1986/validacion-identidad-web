package co.com.santander.adapters.secondary.rest.clients;


import co.com.santander.adapters.common.payload.GeneralPayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.ClientePayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.PreguntasPayload;
import co.com.santander.adapters.secondary.rest.identidad.payload.ValidarPreguntasPayload;
import co.com.santander.core.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;

@FeignClient(name = "${evidente-master.service-name}", url = "${evidente-master.url}")
public interface IdentificacionCliente {

    @RequestMapping(method = RequestMethod.POST, value = "/validar")
    ResponseEntity<ResponseDTO> validacionIdentidad(GeneralPayload<ClientePayload> payload) throws RestClientException;

    @RequestMapping(method = RequestMethod.POST, value = "/preguntas")
    ResponseEntity<ResponseDTO> generarPreguntas(GeneralPayload<PreguntasPayload> payload);

    @RequestMapping(method = RequestMethod.POST, value = "/verificar")
    ResponseEntity<ResponseDTO> validarPreguntas(GeneralPayload<ValidarPreguntasPayload> payload);


}
