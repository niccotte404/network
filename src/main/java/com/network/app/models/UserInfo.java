package com.network.app.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    private UUID id;
    private String username;
    private String email;
    private String description;
    private String imageUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
