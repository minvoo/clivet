package com.teamone.clivet.utils;

import com.teamone.clivet.service.PetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CurrentUserUtils {

    private PetService petService;

    public static  String getCurrentUserName(){
        String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return currentUserName;
    }
}
