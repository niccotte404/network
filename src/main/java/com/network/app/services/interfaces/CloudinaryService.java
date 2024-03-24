package com.network.app.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadImage(MultipartFile image);
    String deleteImage(String publicImageId);
}
