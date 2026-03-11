package com.lumiera.shop.lumierashop.service;

import com.lumiera.shop.lumierashop.domain.User;
import com.lumiera.shop.lumierashop.dto.request.RegisterForm;
import com.lumiera.shop.lumierashop.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthMapper authMapper;
    private final PasswordEncoder passwordEncoder;

    public boolean isUsernameDuplicated(String username) {
        return authMapper.existsByUsername(username);
    }

    public void register(RegisterForm request) {
        authMapper.save(new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword())
        ));
    }
}
