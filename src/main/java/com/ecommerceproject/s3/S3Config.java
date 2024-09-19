package com.ecommerceproject.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {
    @Value("${aws_access_key}")
    private String accessKey ;

    @Value("${aws_secret_key}")
    private String secretKey ;
    @Bean
    public S3Client s3Client(){
        Region region = Region.AP_NORTHEAST_2;
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);
        return S3Client.builder().credentialsProvider(credentialsProvider)
                .region(region)
                .build();
    }

}