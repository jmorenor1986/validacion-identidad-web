package co.com.santander.adapters.primary.rest.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ItacErrorException extends RuntimeException {

    private String status;

    public ItacErrorException(String message, Throwable cause, String status) {
        super(message, cause);
        this.status = status;
    }

    public String status() {
        return this.status;
    }

}
