package com.xitter.app.security;

import com.xitter.app.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security, AuthenticationSuccessHandler successHandler){
        security.authorizeHttpRequests(config -> config
                .requestMatchers("/home").hasRole("USER")
                .requestMatchers("/search").hasRole("USER")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/post/**").hasRole("USER")
                .requestMatchers("/register/**").permitAll()
                .anyRequest().authenticated()
        ).formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .successHandler(successHandler)
                .permitAll()
        ).logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/login?logout")
        ).exceptionHandling(configurer -> configurer
                .accessDeniedPage("/access-denied")
        );

        return security.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService service){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider(service);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
