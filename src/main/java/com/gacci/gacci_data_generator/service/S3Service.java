package com.gacci.gacci_data_generator.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class S3Service {

    private final AmazonS3 s3client;
    private final static String BUCKET_NAME = "gacci-data-bucket";

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public String uploadFile(MultipartFile file) {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            s3client.putObject(BUCKET_NAME, uniqueFileName, file.getInputStream(), metadata);

            return s3client.getUrl(BUCKET_NAME, uniqueFileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar o arquivo para o S3", e);
        }
    }

    public byte[] downloadFile(String fileUrl) {

    String fileKey = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);

    S3Object s3Object = s3client.getObject(new GetObjectRequest(BUCKET_NAME, fileKey));
    try (S3ObjectInputStream inputStream = s3Object.getObjectContent()) {
        return IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
        throw new RuntimeException("Erro ao baixar o arquivo do S3", e);
    }
}


}
