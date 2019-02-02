package com.hotel.auth;

import com.hotel.auth.mapper.HotelMapper;
import com.hotel.auth.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
@EnableDiscoveryClient
@EnableResourceServer
@EnableAuthorizationServer
public class AuthServiceApplication {

    @Bean
    @Primary
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    @Primary
    public HotelMapper hotelMapper() {
        return Mappers.getMapper(HotelMapper.class);
    }

    @Bean
    public TokenGranter clientTokenGranter(AuthorizationServerTokenServices customTokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        return new ClientCredentialsTokenGranter(customTokenServices, clientDetailsService, requestFactory);
    }

    @Bean
    public OAuth2RequestFactory clientOAuth2RequestFactory(ClientDetailsService clientDetailsService) {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }


    public static void main(String[] args) {

        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
