package co.com.santander.core.validator;

import co.com.santander.core.dto.ResponseDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidarEstadoAspect {
    @AfterReturning(pointcut = "@annotation( co.com.santander.core.validator.ValidarEstado)", returning = "returnValue")
    public void validateResponse(JoinPoint joinPoint, Object returnValue) {
        if (returnValue instanceof ResponseDTO)
            if (((ResponseDTO) returnValue).getCodRespuesta().equals("1"))
                throw new RuntimeException("Error con los datos");
    }
}
