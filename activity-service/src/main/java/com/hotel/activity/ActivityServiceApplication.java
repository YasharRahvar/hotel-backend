package com.hotel.activity;

import com.hotel.activity.mapper.ActivityMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan("com.hotel")
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration.class})
@EnableDiscoveryClient
//@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class ActivityServiceApplication {

    @Bean
    @Primary
    public ActivityMapper mapper() {
        return Mappers.getMapper(ActivityMapper.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(ActivityServiceApplication.class, args);
    }
}
