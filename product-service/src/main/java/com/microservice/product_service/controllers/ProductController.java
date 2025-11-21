package com.microservice.product_service.controllers;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.microservice.product_service.business.dtos.requests.ProductRequest;
import com.microservice.product_service.business.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    ResponseEntity<?> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllProducts(page - 1, size));
    }

    @PostMapping(value = "/createProduct", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    ResponseEntity<?> createProduct(
            @RequestPart("request") ProductRequest request, // Metin verisi için ProductRequest DTO'su
            @RequestPart("imageFile") MultipartFile imageFile // Dosya için MultipartFile
    ) {
        InputStream inputStream;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(imageFile.getContentType());

        try {
            inputStream = imageFile.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok(productService.createProduct(request, inputStream, objectMetadata));
    }
}
