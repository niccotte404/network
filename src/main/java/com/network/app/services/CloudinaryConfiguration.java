package com.network.app.services;

import com.cloudinary.Cloudinary;
import com.network.app.data.constants.CloudinaryConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfiguration {

    @Bean
    public Cloudinary cloudinaryConfig() {
        Map<String, String> configuration = new HashMap<>();
        configuration.put("cloud_name", CloudinaryConstant.CLOUD_NAME);
        configuration.put("api_key", CloudinaryConstant.API_KEY);
        configuration.put("api_secret", CloudinaryConstant.API_SECRET);

        return new Cloudinary(configuration);
    }
}
