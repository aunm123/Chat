package com.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadPicture(MultipartFile picture);
}
