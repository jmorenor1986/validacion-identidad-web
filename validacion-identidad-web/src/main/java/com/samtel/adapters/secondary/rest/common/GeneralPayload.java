package com.samtel.adapters.secondary.rest.common;

import org.springframework.web.bind.annotation.RequestHeader;

public class GeneralPayload <T> {

    private RequestHeader requestHeader;
    private T requestBody;
}