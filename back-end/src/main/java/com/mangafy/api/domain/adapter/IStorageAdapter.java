package com.mangafy.api.domain.adapter;

import java.io.InputStream;

public interface IStorageAdapter {
    public String upload(String objectName, InputStream stream, String contentType, long size) throws Exception;
    public InputStream download(String objectName) throws Exception;
}
