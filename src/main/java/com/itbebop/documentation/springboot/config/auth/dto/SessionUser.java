package com.itbebop.documentation.springboot.config.auth.dto;

import com.itbebop.documentation.springboot.domain.user.User;

import java.io.Serializable;

public class SessionUser implements Serializable { // 인증된 사용자 정보만 필요함
    private String name;
    private String email;
    private String picture;
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
