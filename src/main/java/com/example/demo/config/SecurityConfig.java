package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
        UserDetails user = User.builder()
                .username("user").password(passwordEncoder.encode("user")).roles("USER").build();
        UserDetails employee = User.builder()
                .username("employee").password(passwordEncoder.encode("employee")).roles("EMPLOYEE").build();

        return new InMemoryUserDetailsManager(user, admin, employee);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Отключение CSRF для API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/resumes/create/*").hasRole("admin")
                        .requestMatchers("/resumes/edit/*").hasAnyRole("ADMIN","USER")
                        .requestMatchers("/resumes/delete/*").hasRole("user")
                        .requestMatchers("/**").permitAll()

                )
                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll)
                .build();
    }
}