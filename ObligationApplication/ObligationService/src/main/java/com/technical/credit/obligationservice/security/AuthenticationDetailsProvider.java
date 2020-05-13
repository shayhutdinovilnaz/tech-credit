package com.technical.credit.obligationservice.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationDetailsProvider {
    public String getAuthenticationAccessToken() {
        OAuth2AuthenticationDetails authenticationDetails =
                (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return authenticationDetails.getTokenValue();
    }

    public String getAuthenticatedPrincipalUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
