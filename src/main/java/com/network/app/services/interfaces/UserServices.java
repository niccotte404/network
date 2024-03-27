package com.network.app.services.interfaces;

import com.network.app.models.UserEntity;
import com.network.app.models.UserInfo;
import com.network.app.models.dto.UserDetailsResponse;
import com.network.app.models.dto.UserInfoDto;

public interface UserServices {
    UserInfo updateUserInfo(UserInfo userInfo);
    UserInfo getUserInfoByUsername(String username);
    UserDetailsResponse getAllUsersInfoDto(int pageNumber, int pageSize);
    UserInfo addUserInfoWithDto(UserInfoDto userInfoDto, String username);
    boolean isCurrentUserEquals(String username);
    UserInfoDto getUserInfoDtoByUsername(String username);
}
