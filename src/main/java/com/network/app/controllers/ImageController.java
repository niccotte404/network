package com.network.app.controllers;

import com.network.app.services.interfaces.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/image/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("img") MultipartFile image) {
        String imageUrl = cloudinaryService.uploadImage(image);

        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }

    @PostMapping("/image/delete/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable("imageId") String imageId) {
        String imageUrl = cloudinaryService.deleteImage(imageId);

        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }
}
