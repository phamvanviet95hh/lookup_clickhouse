package com.example.clickhouse.common.config;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@Log4j2
@Component
public class Servant {

    public String getUsername() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return ((Claims) auth.getCredentials()).get("sub", String.class);
            return ((Claims) auth.getCredentials()).get("username", String.class);
        } catch (Exception e) {
//            log.warn("Error when get username from token: ", e);
            return "";
        }

    }

}
