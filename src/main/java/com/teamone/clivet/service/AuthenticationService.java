package com.teamone.clivet.service;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.dto.UserLoginDto;

public interface AuthenticationService {

    UserLoginDto signInAndReturnJWT(User signInRequest);
    boolean isAdmin();
    boolean isLogged();
}
