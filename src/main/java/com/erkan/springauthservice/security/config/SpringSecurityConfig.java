package com.erkan.springauthservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123")
            .roles("ADMIN")
            .build();
    
            UserDetails user = org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder()
            .username("user")
            .password("123")
            .roles("USER")
            .build();

            return new InMemoryUserDetailsManager(admin, user);
        }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.csrf(crsf -> crsf.disable())
       .authorizeHttpRequests(authorize 
            -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")       
                .requestMatchers("/admin/**").hasRole("ADMIN")     
                .anyRequest().authenticated()
       ) // we will add our own ant matchers later
       .httpBasic(Customizer.withDefaults()).build();
    }
}
