package com.hotel.product.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.hotel.product.security.SecurityConstants.*;
import static java.util.stream.Collectors.toSet;

/**
 * Class security utils.
 */
public final class SecurityUtil {

    /**
     * Private constructor.
     */
    private SecurityUtil() { }

    /**
     * Method that returns hotel id, fetching it from oauth2 authentication.
     * @param authentication OAuth2 authentication instance
     * @return hotel id value
     */
    public static final Long getHotelId(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        return Long.valueOf((Integer)decodedDetails.getOrDefault(HOTEL_ID, -1));
    }

    /**
     * Method that return a user id, fetching it from oauth2 authentication.
     * @param authentication OAuth2 authentication instance
     * @return User id value
     */
    public static final Long getUserId(final OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        return Long.valueOf((Integer)decodedDetails.getOrDefault(USER_ID, -1));
    }

    /**
     * Method that return a user id, fetching it from oauth2 authentication.
     * @param authentication OAuth2 authentication instance
     * @return User id value
     */
    public static final Boolean getIsProxy(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        return (Boolean) decodedDetails.getOrDefault(IS_PROXY, false);
    }

    /**
     * Method that return a user name key, fetching it from oauth2 authentication.
     * @param authentication OAuth2 authentication instance
     * @return User name key value
     */
    public static final String getSurveyorName(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        return (String) decodedDetails.get(NAME_KEY);
    }

    /**
     * Method that return a user name, fetching it from oauth2 authentication.
     * @param authentication OAuth2 authentication instance
     * @return User name value
     */
    public static final String getUserName(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        return (String) decodedDetails.get(USER_NAME);
    }

    /**
     * Method that return a team id, fetching it from oauth2 authentication.
     * @param authentication OAuth2 authentication instance
     * @return team id value
     */
    public static final Long getTeamId(OAuth2Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)authentication.getDetails();
        Map<String, Object> decodedDetails = (Map<String, Object>) details.getDecodedDetails();
        return Long.valueOf((Integer)decodedDetails.getOrDefault(TEAM_ID, -1));
    }

    /**
     * Method that converts GrantedAuthority to String of roles
     * @param authorities
     * @return
     */
    public static final Set<String> getRolesSet(Collection<GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(toSet());
    }

}
