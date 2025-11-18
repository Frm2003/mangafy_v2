package com.mangafy.api.adapters.controller;

import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangafy.api.adapters.adapter.IStorageAdapter;


@RestController
@RequestMapping("/storage")
public class StorageController {

    private final IStorageAdapter storageAdapter;

    public StorageController(IStorageAdapter storageService) {
        this.storageAdapter = storageService;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws Exception {
        try (InputStream inputStream = storageAdapter.download("storage/download/" + fileName)) {
            byte[] content = inputStream.readAllBytes();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);
        }
    }
}
