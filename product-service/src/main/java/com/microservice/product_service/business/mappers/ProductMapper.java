package com.microservice.product_service.business.mappers;

import com.microservice.product_service.business.dtos.requests.ProductRequest;
import com.microservice.product_service.business.dtos.responses.ProductResponse;
import com.microservice.product_service.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    Product toEntity(ProductRequest request);
}
