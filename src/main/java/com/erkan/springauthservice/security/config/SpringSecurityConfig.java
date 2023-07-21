package com.erkan.springauthservice.security.config;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.erkan.springauthservice.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final CustomUserDetailsService customUserDetailsService; 
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       return http.csrf(crsf -> crsf.disable())
       .authorizeHttpRequests(authorize 
            -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")    
                .requestMatchers("/admin/**").hasAuthority("ADMIN")     
                .anyRequest().authenticated()
       ).sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
       .httpBasic(Customizer.withDefaults()).build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
       builder.userDetailsService(customUserDetailsService)
               .passwordEncoder(passwordEncoder);
   }
}
