package com.network.app.controllers;

import com.network.app.models.UserInfo;
import com.network.app.models.dto.LoginDto;
import com.network.app.models.dto.RegisterDto;
import com.network.app.models.Role;
import com.network.app.models.UserEntity;
import com.network.app.models.dto.UserDetailsDto;
import com.network.app.repositories.RoleRepository;
import com.network.app.repositories.UserRepository;
import com.network.app.security.CustomUserDetailsService;
import com.network.app.security.jwt.JwtTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenMapper jwtTokenMapper;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthController(UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder, JwtTokenMapper jwtTokenMapper, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenMapper = jwtTokenMapper;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDto.getUsername());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        String token = jwtTokenMapper.generateToken(userDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        return new ResponseEntity<>(token, HttpStatus.OK);

    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername()))
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setUser(user);

        user.setUserInfo(userInfo);

        userRepository.save(user);

        return new ResponseEntity<>("User register success!", HttpStatus.OK);
    }
}
