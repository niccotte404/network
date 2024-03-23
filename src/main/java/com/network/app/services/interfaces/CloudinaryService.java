package com.network.app.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadImageAsync(MultipartFile image);
    String deleteImageAsync(String publicImageId);
}
