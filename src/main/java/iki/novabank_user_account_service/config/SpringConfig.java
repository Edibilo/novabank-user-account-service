package iki.novabank_user_account_service.config;

import iki.novabank_user_account_service.security.JwtAuthenticationEntry;
import iki.novabank_user_account_service.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SpringConfig {

    private final JwtAuthenticationEntry jwtAuthenticationEntry;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SpringConfig(JwtAuthenticationEntry jwtAuthenticationEntry,
                        JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationEntry = jwtAuthenticationEntry;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {

                    //roles
                    request.requestMatchers("/api/v1/roles/**").hasRole("ADMIN");

                    // Public Actuator health endpoint
                    request.requestMatchers("/actuator/health").permitAll();

                    // Admin-only Actuator endpoints
                    request.requestMatchers("/actuator/**").hasRole("ADMIN");

                    //register & login
                    request.requestMatchers("/api/v1/auth/**").permitAll();

                    request.anyRequest().authenticated();
                });
        httpSecurity.exceptionHandling(exception ->
                exception.authenticationEntryPoint(jwtAuthenticationEntry));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
