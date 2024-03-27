package com.network.app.services.impl;

import com.network.app.exceptions.exceptions.UsernameNotFoundException;
import com.network.app.models.UserEntity;
import com.network.app.models.UserInfo;
import com.network.app.models.dto.UserDetailsResponse;
import com.network.app.models.dto.UserDetailsDto;
import com.network.app.models.dto.UserInfoDto;
import com.network.app.repositories.UserInfoRepository;
import com.network.app.repositories.UserRepository;
import com.network.app.services.interfaces.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;;

    @Autowired
    public UserServicesImpl(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUsername(String username) {
        return userInfoRepository.findUserInfoByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public UserDetailsResponse getAllUsersInfoDto(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserInfo> pageOfUsersInfo = userInfoRepository.findAll(pageable);

        List<UserInfo> usersInfoContent = pageOfUsersInfo.getContent();
        List<UserDetailsDto> usersInfoDto = usersInfoContent.stream().map(this::mapUserDetailsToDto).toList();

        return UserDetailsResponse.builder()
                .content(usersInfoDto)
                .pageNumber(pageOfUsersInfo.getNumber())
                .pageSize(pageOfUsersInfo.getSize())
                .totalElement(pageOfUsersInfo.getTotalElements())
                .totalPages(pageOfUsersInfo.getTotalPages())
                .isLast(pageOfUsersInfo.isLast())
                .build();
    }

    @Override
    public UserInfo addUserInfoWithDto(UserInfoDto userInfoDto, String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(username);
        userInfo.setEmail(userInfoDto.getEmail());
        userInfo.setDescription(userInfoDto.getDescription());
        userInfo.setImageUrl(userInfoDto.getImageUrl());
        userInfo.setUser(user);

        user.setUserInfo(userInfo);

        userRepository.save(user);
        userInfoRepository.save(userInfo);

        return userInfo;
    }

    @Override
    public boolean isCurrentUserEquals(String username) {
        String actualUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return actualUser.equals(username);
    }

    @Override
    public UserInfoDto getUserInfoDtoByUsername(String username) {
        return mapUserInfoToDto(userInfoRepository.findUserInfoByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found")));
    }

    private UserDetailsDto mapUserDetailsToDto(UserInfo userInfo){
        return UserDetailsDto.builder()
                .username(userInfo.getUsername())
                .imageUrl(userInfo.getImageUrl())
                .build();
    }

    private UserInfoDto mapUserInfoToDto(UserInfo userInfo){
        return UserInfoDto.builder()
                .username(userInfo.getUsername())
                .email(userInfo.getEmail())
                .description(userInfo.getDescription())
                .imageUrl(userInfo.getImageUrl())
                .build();
    }
}
