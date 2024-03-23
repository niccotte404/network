package com.network.app.services.impl;

import com.network.app.exceptions.exceptions.UserIdNotFoundException;
import com.network.app.exceptions.exceptions.UsernameNotFoundException;
import com.network.app.models.UserEntity;
import com.network.app.models.UserInfo;
import com.network.app.repositories.UserInfoRepository;
import com.network.app.repositories.UserRepository;
import com.network.app.services.interfaces.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo, String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found in table users"));
        userInfo.setId(user.getId());
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found in table users"));
        return userInfoRepository.findById(user.getId()).orElseThrow(() -> new UserIdNotFoundException("User ID not found in table user_info"));
    }
}
