package com.lumiera.shop.lumierashop.domain;

import com.lumiera.shop.lumierashop.domain.enums.Role;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class User {

    private String username;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
