package com.microservice.product_service.business.managers;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.microservice.product_service.business.dtos.requests.ProductRequest;
import com.microservice.product_service.business.dtos.responses.ProductResponse;
import com.microservice.product_service.business.mappers.ProductMapper;
import com.microservice.product_service.business.services.ProductService;
import com.microservice.product_service.business.services.S3Service;
import com.microservice.product_service.entities.Product;
import com.microservice.product_service.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final S3Service s3Service;

    @Override
    public Page<ProductResponse> getAllProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        return repository.findAll(pageRequest)
                .map(mapper::toResponse);
    }

    @Override
    public ProductResponse createProduct(ProductRequest request, InputStream inputStream, ObjectMetadata objectMetadata) {
        Product product = mapper.toEntity(request);
        String key = product.getName() + "-" + UUID.randomUUID().toString();
        s3Service.uploadImage(key, inputStream, objectMetadata);
        String imageUrl = s3Service.getImageUrl(key);
        product.setPhotoUrl(imageUrl);
        return mapper.toResponse(repository.save(product));
    }
}
