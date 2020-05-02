package co.com.santander.core.validator;

import co.com.santander.adapters.primary.rest.common.exception.ItacErrorException;
import co.com.santander.core.dto.ResponseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidarEstadoAspect {
    @AfterReturning(pointcut = "@annotation( co.com.santander.core.validator.ValidarEstado)", returning = "returnValue")
    public void validateResponse(JoinPoint joinPoint, Object returnValue) {
        ResponseEntity<ResponseDTO> response = (ResponseEntity<ResponseDTO>) returnValue;
        if (response.getBody() instanceof ResponseDTO)
            if (!"1".equals(response.getBody().getCodRespuesta()))
                throw new ItacErrorException(response.getBody().getMensajeError().concat("@").concat(response.getBody().getCodRespuesta()),
                        new Throwable(response.getBody().getRespuestaServicio().toString()));
    }
}
