package com.example.surpresaRafaela.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Libera todas as rotas da nossa API
                .allowedOrigins("*") // Libera qualquer site para acessar (facilita na hora de usar o Ngrok)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}