package springboot.mvcapp.employees.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) {
        security.authorizeHttpRequests(configurer -> configurer.requestMatchers("/").hasRole("EMPLOYEE").requestMatchers("/leaders").hasRole("MANAGER").requestMatchers("/systems").hasRole("ADMIN").anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").loginProcessingUrl("/authenticate").permitAll()).logout(logout -> logout.permitAll()).exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));
        return security.build();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
}
