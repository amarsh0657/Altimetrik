package org.altimetrik.automotiveims.config;

import jakarta.servlet.http.HttpServletRequest;
import org.altimetrik.automotiveims.security.JwtAuthenticationEntryPoint;
import org.altimetrik.automotiveims.security.JwtAuthenticationFilter;
import org.altimetrik.automotiveims.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint point;


    @Autowired
    private JwtAuthenticationFilter filter;


    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //http.csrf(csrf -> csrf.disable())
        http.cors().
                configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        //configuration.setAllowedOrigins(Collections.singletonList("***"));
                          configuration.setAllowedOrigins(Collections.singletonList("https://admin.hitechdotcom.org/,https://www.hitechdotcom.org,https://hitechdotcom.org, http://localhost:4200/, http://localhost:8081/"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);
                        return configuration;
                    }
                }).and()
                .csrf().disable()

                // authentication  require

                .authorizeRequests()
                /* Page CRUD */
                .requestMatchers("/v1/part/***").authenticated()
                .requestMatchers("/v1/supplier/***").authenticated()
                .requestMatchers("/v1/vehicle/***").authenticated()
                .requestMatchers("/v1/salesTransaction/***").authenticated()
             //   .requestMatchers("/swagger-ui/***").permitAll()

                //public
                .requestMatchers("/v1/auth/***").permitAll()


                // .requestMatchers("/v1/auth/createUser").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }


}
