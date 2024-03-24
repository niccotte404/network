package com.network.app.controllers;

import com.network.app.models.UserInfo;
import com.network.app.models.dto.UserDetailsResponse;
import com.network.app.services.interfaces.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user/")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserServices userServices;

    @GetMapping("details/{username}")
    public ResponseEntity<UserInfo> getUserDetails(@PathVariable("username") String username) {
        UserInfo userDetails = userServices.getUserInfoByUsername(username);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<UserDetailsResponse> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return new ResponseEntity<>(userServices.getAllUsersInfoDto(pageNumber, pageSize), HttpStatus.OK);
    }

    // todo: create changing and adding UserInfo to current authenticated account with current session
}
