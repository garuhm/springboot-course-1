package springboot.mvcapp.employees.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails nhi = User.builder()
                .username("nhi")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails jackelyn = User.builder()
                .username("jackelyn")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails gabe = User.builder()
                .username("gabe")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jackelyn, nhi, gabe);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) {
        security.authorizeHttpRequests(configurer -> configurer.anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").loginProcessingUrl("/authenticate").permitAll());
        return security.build();
    }
}
