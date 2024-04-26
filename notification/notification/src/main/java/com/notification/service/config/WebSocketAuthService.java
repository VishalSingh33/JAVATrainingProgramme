package com.notification.service.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketAuthService {

    private final TokenProvider tokenProvider;

    public UsernamePasswordAuthenticationToken attemptAuthentication(String authorizationHeaderValue) {
        try {
            if (isValidAuthorizationHeaderValue(authorizationHeaderValue)) {
                var token = authorizationHeaderValue.replace("Bearer ", "");

                if(tokenProvider.validateToken(token)) {
                    return new UsernamePasswordAuthenticationToken(tokenProvider.getUsername(token), "null");
                }
            }
        } catch(Exception e) {
            log.info("Exception: {}", e);
        }
        return null;
    }

    public boolean isValidAuthorizationHeaderValue(String authHeaderValue) {
        return StringUtils.hasText(authHeaderValue)
            && authHeaderValue.contains("Bearer ")
            && StringUtils.hasText(authHeaderValue.replace("Bearer ", ""));
    }
}
