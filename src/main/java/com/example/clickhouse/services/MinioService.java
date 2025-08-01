package com.example.clickhouse.services;

import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.Tags;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class MinioService {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucketName;

    private MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    public String getFileAsBase64(String fullPath) throws Exception {
        String objectPath = fullPath.replace(bucketName + "/", "");

        try (InputStream inputStream = this.minioClient().getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectPath)
                        .build()
        )) {
            byte[] fileBytes = inputStream.readAllBytes();
            return Base64.getEncoder().encodeToString(fileBytes);
        }
    }

    public String uploadFile(String fileName, MultipartFile file) throws Exception {
        try {
            boolean bucketExists = this.minioClient().bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                this.minioClient().makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            try (InputStream inputStream = file.getInputStream()) {
                this.minioClient().putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .stream(inputStream, inputStream.available(), -1) // Specify size of stream
                                .contentType(file.getContentType())
                                .build());
            }
            log.info("save file {} onto: {}", file.getOriginalFilename(), fileName);
            return fileName;
        } catch (MinioException e) {
            throw new Exception("Error occurred: " + e);
        }
    }

    public String uploadFile(String rootPath, byte[] imageBytes, String fileName, String contentType) throws Exception {
        try {
            var fullFilePath = rootPath + fileName + "." + contentType.split("/")[1];
            boolean bucketExists = this.minioClient().bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                this.minioClient().makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes)) {
                this.minioClient().putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fullFilePath)
                                .stream(inputStream, inputStream.available(), -1)
                                .contentType(contentType)
                                .build());
            }
            log.info("save file: {}", fullFilePath);
            return fullFilePath;
        } catch (MinioException e) {
            throw new Exception("Error occurred: " + e);
        }
    }

    public void uploadFile(String fileName, byte[] imageBytes) throws Exception {
        try {
            boolean bucketExists = this.minioClient().bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                this.minioClient().makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            try (ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes)) {
                Map<String, String> newTags = new HashMap<>();
                newTags.put("etl_status", "data_received");
                this.minioClient().putObject(
                        PutObjectArgs.builder()
                                .bucket(bucketName)
                                .object(fileName)
                                .tags(newTags)
                                .stream(inputStream, inputStream.available(), -1)
                                .contentType(MediaType.APPLICATION_XML_VALUE)
                                .build());
            }
            log.info("save file: {}", fileName);
        } catch (MinioException e) {
            throw new Exception("Error occurred: " + e);
        }
    }

    public InputStream downloadFile(String fullFilePath) throws Exception {
        try {
            var builder = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fullFilePath).build();
            InputStream object = this.minioClient().getObject(builder);
            log.info("download file: {}", fullFilePath);
            return object;
        } catch (Exception e) {
            throw new Exception("Error occurred: " + e);
        }
    }
    public boolean doesObjectExist(String objectName) {
        try {
            // Check if the object exists
            minioClient().statObject(
                    StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
            return true; // Object exists
        } catch (MinioException e) {
            if (e.getMessage().isEmpty()) {
                return false;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Tags getObjectTags(String objectName) {
        try {
            // Get object tags
            return minioClient().getObjectTags(
                    GetObjectTagsArgs.builder()
                            .bucket(bucketName)
                            .object(objectName)
                            .build()
            );
        } catch (MinioException e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        }
    }

    public void copyFile(String sourceObjectName, String targetObjectName) throws MinioException {
        try {
            this.minioClient().copyObject(
                    CopyObjectArgs.builder()
                            .source(CopySource.builder()
                                    .bucket(bucketName)
                                    .object(sourceObjectName)
                                    .build())
                            .bucket(bucketName)
                            .object(targetObjectName)
                            .build());

            log.info("File copied successfully.");
        } catch (Exception e) {
            throw new MinioException("Error occurred: " + e.getMessage());
        }
    }

}
