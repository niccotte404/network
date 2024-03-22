package com.network.app.services.interfaces;

import com.network.app.models.UserInfo;

public interface UserServices {
    UserInfo updateUserInfo(UserInfo userInfo, String username);
    UserInfo getUserInfoByUsername(String username);
}
