package co.com.santander.adapters.primary.rest.common.handler;

import co.com.santander.adapters.primary.rest.common.exception.ItacErrorException;
import co.com.santander.core.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings({"unchecked", "rawtypes"})
@ControllerAdvice
public class ControllerHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ItacErrorException.class)
    public final ResponseEntity<ResponseDTO> itacErrorException(ItacErrorException exc, WebRequest request) {
        String[] error = exc.getMessage().split("@");
         return new ResponseEntity<>(ResponseDTO.builder()
                .mensajeError(error[0])
                .respuestaServicio(exc.getCause().getMessage())
                .codRespuesta(error[1])
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
