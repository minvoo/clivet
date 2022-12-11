package com.teamone.clivet.security;

import com.teamone.clivet.model.user.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class UserPrinciple {

    private long id;
    private String username;
    transient private String password;
    transient private User user;
    private Set<GrantedAuthority> authorities;
}
