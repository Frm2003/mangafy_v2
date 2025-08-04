package com.mangafy.api.application.controller;

import com.mangafy.api.application.service.IStorageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/storage")
public class StorageControllerTest {

    private final IStorageService storageService;

    public StorageControllerTest(IStorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String objectName = file.getOriginalFilename();
        storageService.upload(
                objectName,
                file.getInputStream(),
                file.getContentType(),
                file.getSize()
        );
        return ResponseEntity.ok(objectName);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws Exception {
        try (InputStream inputStream = storageService.download("storage/download/" + fileName)) {
            byte[] content = inputStream.readAllBytes();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);
        }
    }
}