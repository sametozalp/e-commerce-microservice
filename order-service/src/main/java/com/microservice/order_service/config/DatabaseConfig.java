package com.microservice.order_service.config;

import com.google.gson.Gson;
import com.microservice.order_service.models.DbSecret;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${aws.secretmanager.secretId}")
    private String secretId;

    @Value("${db.url}")
    private String url;

    @Bean
    public DataSource getSource() {
        String secret = getSecret();
        DbSecret dbSecret = parseSecret(secret);

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(dbSecret.getUsername());
        hikariDataSource.setPassword(dbSecret.getPassword());
        hikariDataSource.setDriverClassName("org.postgresql.Driver");
        return hikariDataSource;
    }

    private DbSecret parseSecret(String secret) {
        return new Gson().fromJson(secret, DbSecret.class);
    }

    public String getSecret() {
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.of("eu-north-1"))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        GetSecretValueRequest secretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretId)
                .build();

        GetSecretValueResponse secretValueResponse = client.getSecretValue(secretValueRequest);
        return secretValueResponse.secretString();
    }

}