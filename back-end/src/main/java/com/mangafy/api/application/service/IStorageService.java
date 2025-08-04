package com.mangafy.api.application.service;

import java.io.InputStream;

public interface IStorageService {
    public String upload(String objectName, InputStream stream, String contentType, long size) throws Exception;
    public InputStream download(String objectName) throws Exception;
}
