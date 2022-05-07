package com.filmapp.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("film.app")
@Getter
@Setter
public class JwtProperties {
    private String jwtSecret;
    private Long jwtExpirationMs;
}
