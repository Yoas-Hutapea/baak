package com.tugasbesar.baak.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.tugasbesar.baak.model.User;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    private User user;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    // You can also add getters for specific properties if you need them
    public String getNoktp() {
        return user.getNoktp();
    }

    public String getNim() {
        return user.getNim();
    }

    public String getNamalengkap() {
        return user.getNamalengkap();
    }

    public String getNohp() {
        return user.getNohp();
    }
}

