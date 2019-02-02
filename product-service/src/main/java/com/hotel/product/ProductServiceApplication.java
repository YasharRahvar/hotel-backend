package com.hotel.product;

import com.hotel.product.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;


@ComponentScan("com.hotel")
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
@EnableDiscoveryClient
public class ProductServiceApplication {

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    @Primary
    public ProductMapper mapper() {
        return Mappers.getMapper(ProductMapper.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(ProductServiceApplication.class, args);
    }

}

