package com.mangafy.api.infra.service;

import com.mangafy.api.application.service.IStorageService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.GetObjectArgs;
import io.minio.ObjectWriteResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StorageService implements IStorageService {

    private final MinioClient client;

    @Value("${storage.bucket-name}")
    private String bucket;

    public StorageService(MinioClient client) {
        this.client = client;
    }

    public String upload(String objectName, InputStream stream, String contentType, long size) throws Exception {
        ObjectWriteResponse response = client.putObject(
                PutObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .stream(stream, size, -1)
                        .contentType(contentType)
                        .build()
        );
        return response.object();
    }

    public InputStream download(String objectName) throws Exception {
        return client.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build()
        );
    }
}
