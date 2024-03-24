package com.network.app.repositories;

import com.network.app.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    Optional<UserInfo> findUserInfoByUsername(String username);
}
