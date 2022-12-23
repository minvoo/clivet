package com.teamone.clivet.service;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.UserRole;
import com.teamone.clivet.model.user.dto.UserLoginDto;
import com.teamone.clivet.security.UserPrinciple;
import com.teamone.clivet.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    /**
     * Signs in and returns JWT token
     *
     * @param signInRequest User with specified username and password to sign in
     * @return Signed in User
     */
    @Override
    public UserLoginDto signInAndReturnJWT(User signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrinciple);

        User signInUser = userPrinciple.getUser();
        signInUser.setToken(jwt);
        UserLoginDto userLoginDto = UserLoginDto.mapToDto(signInUser);

        return userLoginDto;
    }

    /**
     * Checks if current User has ADMIN role.
     *
     * @return "true" if user has Admin role, "false" if user has other role.
     */
    @Override
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        Optional<User> currentUserEntityOptional = userService.findByUsername(currentUserName);
        if (currentUserEntityOptional.isPresent()) {
            User currentUserEntity = currentUserEntityOptional.get();
            if (currentUserEntity.getRole().equals(UserRole.ADMIN)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if client is logged in.
     * @return "true" if client is logged in, "false" if client is not logged in
     */
    @Override
    public boolean isLogged () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }
}