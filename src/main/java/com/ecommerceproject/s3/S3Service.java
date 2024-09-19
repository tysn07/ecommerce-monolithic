package com.ecommerceproject.s3;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

@Builder
@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3;

    public void putObject(String bucketName, String key, MultipartFile file) throws IOException {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .contentType("image/png")
                .key(key)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .build();
        RequestBody requestBody = RequestBody
                .fromInputStream(file.getInputStream(),file.getSize());

        s3.putObject(objectRequest, requestBody);
    }
}
