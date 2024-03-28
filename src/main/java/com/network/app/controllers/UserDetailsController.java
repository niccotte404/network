package com.network.app.controllers;

import com.network.app.models.dto.UserDetailsResponse;
import com.network.app.models.dto.UserInfoDto;
import com.network.app.services.interfaces.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/")
public class UserDetailsController {

    private final UserServices userServices;

    @GetMapping("details/{username}")
    public ResponseEntity<UserInfoDto> getUserDetails(@PathVariable("username") String username) {
        UserInfoDto userDetails = userServices.getUserInfoDtoByUsername(username);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<UserDetailsResponse> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(userServices.getAllUsersInfoDto(pageNumber, pageSize), HttpStatus.OK);
    }

    @PostMapping("details/{username}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<String> addUserInfo(
            @RequestBody UserInfoDto userInfoDto,
            @PathVariable("username") String username
    ) {
        if (!userServices.isCurrentUserEquals(username))
            return new ResponseEntity<>("User details can not be added", HttpStatus.BAD_REQUEST);

        userServices.addOrUpdateUserInfoWithDto(userInfoDto, username);
        return new ResponseEntity<>("User details successfully added", HttpStatus.OK);
    }

    @PutMapping("details/{username}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<String> updateUserInfo(
            @RequestBody UserInfoDto userInfoDto,
            @PathVariable("username") String username
    ) {
        if (!userServices.isCurrentUserEquals(username))
            return new ResponseEntity<>("User details can not be added", HttpStatus.BAD_REQUEST);

        userServices.addOrUpdateUserInfoWithDto(userInfoDto, username);
        return new ResponseEntity<>("User details successfully added", HttpStatus.OK);
    }
}
