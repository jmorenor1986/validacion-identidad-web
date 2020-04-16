package co.com.santander.adapters.common.payload;

import lombok.Data;

@Data
public class GeneralPayload<T> {

    private RequestHeader requestHeader;
    private T requestBody;
}