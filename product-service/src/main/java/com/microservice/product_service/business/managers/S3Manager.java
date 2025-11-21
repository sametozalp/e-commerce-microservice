package com.microservice.product_service.business.managers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.microservice.product_service.business.services.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class S3Manager implements S3Service {

    private final AmazonS3 s3;

    @Override
    public void uploadImage(String key, InputStream inputStream, ObjectMetadata metadata) {
        s3.putObject("microservice-product-fdsifdsfd", key, inputStream, metadata);
    }

    @Override
    public String getImageUrl(String key) {
        return s3.getUrl("microservice-product-fdsifdsfd", key).toString();
    }
}
