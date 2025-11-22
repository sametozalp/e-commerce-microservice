package com.microservice.order_service.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    private String customerNumber;
    private String productId;
    private int quantity;

}
