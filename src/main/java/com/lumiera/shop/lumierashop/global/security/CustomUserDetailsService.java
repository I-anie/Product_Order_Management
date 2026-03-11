package com.lumiera.shop.lumierashop.global.security;

import com.lumiera.shop.lumierashop.mapper.AuthMapper;
import com.lumiera.shop.lumierashop.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authMapper.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("username not found: username = " + username);

        return new CustomUserDetails(user.getUsername(), user.getPassword(), user.getRole());
    }
}
