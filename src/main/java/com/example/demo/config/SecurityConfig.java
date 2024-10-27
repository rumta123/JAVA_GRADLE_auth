package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Отключение CSRF (для тестирования)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Разрешить доступ к маршрутам /auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Доступ только для ADMIN
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Доступ для USER или ADMIN
                        .anyRequest().authenticated() // Остальные запросы требуют аутентификации
                )
                .httpBasic(); // Используйте базовую аутентификацию

        return http.build();
    }
}
