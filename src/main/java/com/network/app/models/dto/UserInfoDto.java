package com.network.app.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {
    private String username;
    private String email;
    private String description;
    private String imageUrl;
}
