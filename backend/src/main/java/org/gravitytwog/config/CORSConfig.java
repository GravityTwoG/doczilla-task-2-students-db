package org.gravitytwog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Value("${org.gravitytwog.cors.allowedOrigins}")
    public List<String> allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedOrigins(this.allowedOrigins.get(0))
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("Accept", "Content-Type", "Cache-Control", "Content-Type");
    }
}
