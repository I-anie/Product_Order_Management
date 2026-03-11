package com.lumiera.shop.lumierashop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lumiera.shop.lumierashop.mapper")
public class LumieraShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(LumieraShopApplication.class, args);
    }

}
