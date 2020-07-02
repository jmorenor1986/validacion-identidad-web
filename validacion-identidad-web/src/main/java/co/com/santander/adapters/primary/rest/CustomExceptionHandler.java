package co.com.santander.adapters.primary.rest;

import co.com.santander.adapters.primary.rest.common.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Response> handlerException(Exception ex, WebRequest request) {
        Response response = new Response();
        response.setCodRespuesta("0");
        response.setMensajeError(ex.getMessage());
        response.setRespuestaServicio(null);
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }
}
