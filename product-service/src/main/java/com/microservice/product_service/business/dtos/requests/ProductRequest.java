package com.microservice.product_service.business.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequest {

    private String name;

    private int price;
}
