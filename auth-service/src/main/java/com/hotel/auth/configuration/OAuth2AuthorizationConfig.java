package com.hotel.auth.configuration;


import com.hotel.auth.security.CustomJwtAccessTokenConverter;
import com.hotel.auth.security.CustomTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * Class oauth2 authorization configuration.
 */
@Configuration
@EnableAuthorizationServer
@RefreshScope
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.token.validity.refresh}")
    private int refreshTokenValidityInSeconds;

    @Value("${security.token.validity.access}")
    private int accessTokenValidityInSeconds;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    /*@Autowired
    private UserService userService;*/

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient("android-app")
                .authorizedGrantTypes("refresh_token", "password")
                .secret("$2a$04$CTmSPMqt4wKOuwgH6DoUFO0bjEgWfeDkbrR6iqqMOCm9i5Z2bhKia")
                .scopes("client")
                .and()
                .withClient("ios-app")
                .authorizedGrantTypes("refresh_token", "password")
                .secret("$2a$04$CTmSPMqt4wKOuwgH6DoUFO0bjEgWfeDkbrR6iqqMOCm9i5Z2bhKia")
                .scopes("client")
                .and()
                .withClient("inventory-service")
                .secret("$2a$10$Lj4OZEhU9mwxKd.sulXO/OtWRofOLiGsLS4CbY9YS0ouZ0/s1qa16")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.inventory")
                .and()
                .withClient("property-service")
                .secret("$2a$10$kFkA6nDqYbAhdTCMzfimJuMHGfvAfU/hQnDL8GGpZhbrFiWFyH5fq")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.property")
                .and()
                .withClient("media-service")
                .secret("$2a$10$yMjmYBuJKl11NmYi0u4yH.eOg/Arz3qp7X/tD5.5Qz2iw0husS0N6")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.media")
                .and()
                .withClient("purchasing-service")
                .secret("$2a$10$l7csVHAteyDAtmz3sultEuKbXX05.auLfnF/SRi7dHPlwq0BZVVyu")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.purchasing")
                .and()
                .withClient("middleware-api")
                .secret("$2a$10$fiLp7JPq/uMAd92GPAS15.axy36SNy3zWpiiaToK77rDpR7t80mJG")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("api.middleware.read", "api.middleware.write")
                .and()
                .withClient("quote-service")
                .secret("$2a$10$rqgFltGVia/1t1RJhSwm8uFKD12FFM/xU1GuOCVAxnlIAPxix.XTK")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.quote")
                .and()
                .withClient("pricing-service")
                .secret("$2a$10$d5dw88bXo0G7mcwl7L7YK.tAtnK3ZMK334tJ/2y42UPHvt8Bmt7Nq")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.pricing")
                .and()
                .withClient("value-service")
                .secret("$2a$10$KGaXxPWMJGUKiyMH0kTpw.K8li1VxpPjyJQXBL.6zao6Z./grGIgm")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.value")
                .and()
                .withClient("policy-service")
                .secret("$2a$10$YBP1xgYOfaQSB9QXBkEgMuU378Of3oQQmGV3S1kz7qlz1SkB7I35K")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.policy")
                .and()
                .withClient("admin-service")
                .secret("$2a$10$RCjzxCNKcBdZ.42zX4Rxe.tEoPT9hv6w8WdAkBgCgDmmgv1k9Ymz6")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.admin")
                .and()
                .withClient("move-service")
                .secret("$2a$09$R4z2X9pjFdV0VWG0pFj9v.2tAHjADptOCvneQfAMf.Tl4h4hb374y")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("service.move");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                .tokenServices(customTokenServices());
                //.userDetailsService(userService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new CustomJwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "mySecretKey".toCharArray()).getKeyPair("jwt");
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Bean
    @Primary
    public AuthorizationServerTokenServices customTokenServices() {
        final CustomTokenServices customTokenServices = new CustomTokenServices();
        final JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        customTokenServices.setTokenStore(jwtTokenStore);
        customTokenServices.setTokenEnhancer(jwtAccessTokenConverter());
        customTokenServices.setClientDetailsService(clientDetailsService);
        customTokenServices.setAccessTokenValiditySeconds(accessTokenValidityInSeconds);
        customTokenServices.setRefreshTokenValiditySeconds(refreshTokenValidityInSeconds);
        customTokenServices.setSupportRefreshToken(true);
        return customTokenServices;
    }



}