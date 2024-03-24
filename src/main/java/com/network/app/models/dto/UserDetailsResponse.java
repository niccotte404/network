package com.network.app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsResponse {
    private List<UserInfoDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean isLast;
}
