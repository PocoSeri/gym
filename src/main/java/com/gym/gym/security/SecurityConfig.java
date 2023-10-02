package com.gym.gym.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)//per un controllo più granulare sui metodi ad esempio   @PreAuthorize("hasRole('ROLE_ADMIN')")
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests((requests) -> requests
                .requestMatchers("").permitAll()
                .anyRequest().authenticated());
        httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        httpSecurity.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      // httpSecurity.cors().configurationSource(corsConfigurationSource());
      /*  httpSecurity.authorizeHttpRequests().requestMatchers("").permitAll()
                .and().authorizeHttpRequests().anyRequest().authenticated() --
                .and().csrf().ignoringAntMatchers("/**")
                .and().headers().frameOptions().sameOrigin()
                .and().addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       */

        return httpSecurity.build();
    }
}
