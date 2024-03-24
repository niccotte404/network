package com.network.app.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.network.app.exceptions.exceptions.ConvertingException;
import com.network.app.exceptions.exceptions.CloudinaryException;
import com.network.app.services.interfaces.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinaryConfig;
    private static final Logger logger = Logger.getLogger(CloudinaryServiceImpl.class.getName());

    @Autowired
    public CloudinaryServiceImpl(Cloudinary cloudinaryConfig) {
        this.cloudinaryConfig = cloudinaryConfig;
    }

    public String uploadImage(MultipartFile image) {
        try {
            File convertedImage = convertMultipartFileToFile(image);
            var uploadResult = cloudinaryConfig.uploader().upload(convertedImage, ObjectUtils.emptyMap());
            boolean isFileDeleted = convertedImage.delete();

            if (isFileDeleted)
                logger.info("Converted image successfully deleted");
            else
                logger.warning("Converted image does not exists");

            return uploadResult.get("url").toString();
        }
        catch (IOException ex) {
            logger.severe("Upload failed");
            throw new CloudinaryException("Failed to upload image to cloudinary account");
        }
    }

    public String deleteImage(String publicImageId){
        try {
            var deletionParams = cloudinaryConfig.uploader().destroy(publicImageId, ObjectUtils.emptyMap());
            return deletionParams.get("result").toString();
        }
        catch (IOException ex) {
            logger.severe("Deletion failed");
            throw new CloudinaryException("Failed to delete image from cloudinary account");
        }
    }

    private File convertMultipartFileToFile(MultipartFile image) {
        try {
            File convertedFile = new File(Objects.requireNonNull(image.getOriginalFilename()));

            FileOutputStream fileStream = new FileOutputStream(convertedFile);
            fileStream.write(image.getBytes());
            fileStream.close();

            return convertedFile;
        }
        catch (Exception ex) {
            logger.severe("Failed to convert image");
            throw new ConvertingException("Failed to convert MultipartFile to File");
        }
    }
}
