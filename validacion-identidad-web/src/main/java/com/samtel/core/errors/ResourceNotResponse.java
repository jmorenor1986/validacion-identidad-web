package com.samtel.core.errors;

public class ResourceNotResponse implements GenericError {

    private final Integer code;
    private final String message;

    public ResourceNotResponse(String message) {
        this.code = 408;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
