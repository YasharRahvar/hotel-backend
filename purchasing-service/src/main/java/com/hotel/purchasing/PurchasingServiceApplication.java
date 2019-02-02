package com.hotel.purchasing;

import com.hotel.purchasing.mapper.PurchaseMapper;
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
public class PurchasingServiceApplication {

	@Bean
	@Primary
	public PurchaseMapper mapper() {

		return Mappers.getMapper(PurchaseMapper.class);
	}

	@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}
	public static void main(String[] args) {
		SpringApplication.run(PurchasingServiceApplication.class, args);
	}

}

