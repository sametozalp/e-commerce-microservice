package com.microservice.order_service.models;

import lombok.Getter;

@Getter
public class DbSecret {

    private String username;
    private String password;
    private String url;
}
