package com.samtel.adapters.primary.rest.identidad;

import com.samtel.adapters.primary.rest.Response;
import com.samtel.adapters.primary.rest.identidad.payload.ClientePayLoad;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/validacion/identidad")
public class ValidarIdentidadController {

    @PostMapping("/")
    public ResponseEntity<Response> validadIdentidad(@RequestBody ClientePayLoad clientePayLoad) {
        Response response = new Response();
        response.setCodRespuesta("1");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
