package com.lumiera.shop.lumierashop.mapper;

import com.lumiera.shop.lumierashop.domain.User;
import org.apache.ibatis.annotations.Param;

public interface AuthMapper {

    boolean existsByUsername(@Param("username") String username);

    int save(@Param("user") User user);

    User findByUsername(@Param("username") String username);
}
