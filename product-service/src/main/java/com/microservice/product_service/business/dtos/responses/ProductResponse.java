package com.microservice.product_service.business.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private int price;

    private String photoUrl;
}
