package com.hotel.activity.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (authentication.getDetails() != null) {
            Map<String, Object> authDetails = (Map)authentication.getDetails();
            ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(authDetails);
        }

        return super.enhance(accessToken, authentication);
    }

    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        Map<String, Object> details = new HashMap();
        this.addDetails(map, details, "userId");
        this.addDetails(map, details, "user_name");
        authentication.setDetails(details);
        return authentication;
    }

    private void addDetails(Map<String, ?> map, Map<String, Object> details, String key) {
        if (map.containsKey(key)) {
            details.put(key, map.get(key));
        }

    }
}
