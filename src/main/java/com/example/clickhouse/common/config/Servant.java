package com.example.clickhouse.common.config;

import com.example.clickhouse.common.gloables.Enums;
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

    public Long getUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return ((Claims) auth.getCredentials()).get("uid", Long.class);
        return ((Claims) auth.getCredentials()).get("id", Long.class);
    }

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


    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAdmin = ((Claims) auth.getCredentials()).get("admin", Boolean.class);
        return isAdmin;
    }

    public String getRole() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return ((Claims) auth.getCredentials()).get("sub", String.class);
            return ((Claims) auth.getCredentials()).get("ut", String.class);
        } catch (Exception e) {
//            log.warn("Error when get username from token: ", e);
            return "";
        }
    }

    public String getMaCskb() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return ((Claims) auth.getCredentials()).get("ma_cskb", String.class);
        } catch (Exception e) {
            return "";
        }
    }

}
