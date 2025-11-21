package com.microservice.product_service.business.services;

import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.InputStream;

public interface S3Service {

    void uploadImage(String key, InputStream inputStream, ObjectMetadata metadata);

    String getImageUrl(String key);

}
