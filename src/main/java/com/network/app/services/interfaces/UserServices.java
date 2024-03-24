package com.network.app.services.interfaces;

import com.network.app.models.UserInfo;
import com.network.app.models.dto.UserDetailsResponse;
import com.network.app.models.dto.UserInfoDto;

import java.util.List;

public interface UserServices {
    UserInfo updateUserInfo(UserInfo userInfo);
    UserInfo getUserInfoByUsername(String username);
    void addUserInfo(UserInfo userInfo);
    UserDetailsResponse getAllUsersInfoDto(int pageNumber, int pageSize);
}
