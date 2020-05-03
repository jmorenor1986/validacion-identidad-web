package co.com.santander.adapters.primary.rest.common.handler;

import co.com.santander.adapters.primary.rest.common.exception.BusinessException;
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
        return new ResponseEntity<>(ResponseDTO.builder()
                .mensajeError(exc.getMessage())
                .respuestaServicio(exc.getCause().getMessage())
                .codRespuesta(exc.status())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ResponseDTO> bussinessException(BusinessException exc, WebRequest request) {
        return new ResponseEntity<>(ResponseDTO.builder()
                .mensajeError(exc.getMessage())
                .respuestaServicio(exc.getCause().getMessage())
                .codRespuesta(exc.status())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
