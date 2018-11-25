package com.hotel.auth.security;

import com.hotel.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import java.util.HashMap;
import java.util.Map;

@RefreshScope
public class CustomTokenServices extends DefaultTokenServices {

    private ClientDetailsService clientDetailsService;

    private static final String JWT_FIELD_USER_ID = "userId";


   /* @Autowired
    private UserService userService;*/

    @Override
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        super.setClientDetailsService(clientDetailsService);
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {

        if(authentication.getPrincipal() instanceof User) {
            final User user = (User) authentication.getPrincipal();
            authentication.setDetails(createAuthenticationDetails(user));
            //userService.updateLastLogin(user.getId());
        }

        return super.createAccessToken(authentication);
    }

    private Map<String, Object> createAuthenticationDetails(final User user) {
        final Map<String, Object> accountInfo = new HashMap<>();

        if(user != null) {
            accountInfo.put(JWT_FIELD_USER_ID, user.getId());
        }
        return accountInfo;
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) throws AuthenticationException {
        isRefreshTokenRequestSupported(tokenRequest);
        return super.refreshAccessToken(refreshTokenValue, tokenRequest);
    }

    private void isRefreshTokenRequestSupported(TokenRequest tokenRequest) {
        if(clientDetailsService != null) {
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(tokenRequest.getClientId());
            if(clientDetails.getAuthorizedGrantTypes().contains("refresh_token")) {
                setSupportRefreshToken(true);
            }
        }
    }
}
