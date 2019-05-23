package com.notification.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public abstract class Request {

    private String requestId;
    public Request(){
        requestId= UUID.randomUUID().toString();
    }
}
