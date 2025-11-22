package com.microservice.order_service.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private Long id;
    private String customerNumber;
    private String productId;
    private int quantity;
    private String date;

}
