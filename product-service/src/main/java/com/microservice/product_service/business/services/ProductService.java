package com.microservice.product_service.business.services;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.microservice.product_service.business.dtos.requests.ProductRequest;
import com.microservice.product_service.business.dtos.responses.ProductResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

import java.io.InputStream;

public interface ProductService {

    Page<ProductResponse> getAllProducts(int page, int size);

    @Nullable ProductResponse createProduct(ProductRequest request, InputStream inputStream, ObjectMetadata objectMetadata);
}
