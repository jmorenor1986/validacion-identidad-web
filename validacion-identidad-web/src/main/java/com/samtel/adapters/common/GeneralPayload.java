package com.samtel.adapters.common;

import lombok.Data;

@Data
public class GeneralPayload<T> {

    private RequestHeader requestHeader;
    private T requestBody;
}