package com.javatalent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.javatalent.service.UserInfoService;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
  
    @Autowired
    private JwtAuthFilter authFilter;

    // User Creation 
    @Bean
    UserDetailsService userDetailsService() { 
        return new UserInfoService(); 
    }

    // Configuring HttpSecurity 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers( 
                        		"/auth/welcome", 
                        		"/auth/addNewUser", 
                        		"/auth/generateToken",
                        		"/api/v1/auth/**",
                        		"/v2/api-docs",
                        		"/v3/api-docs",
                        		"/v3/api-docs/**",
                        		"/swagger-resources",
                        		"/swagger-resources/**",
                        		"/configuration/ui",
                        		"/configuration/security",
                        		"/swagger-ui/**",
                        		"/webjars/**",
                        		"/swagger-ui.html"
                        ).permitAll())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/auth/user/**").authenticated())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/auth/admin/**").authenticated())
                .authorizeHttpRequests(requests -> requests.requestMatchers("/api/**").authenticated())
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); 
    } 
  
    // Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
  
    @Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
    
    // To add Authorize button on the top of swagger UI
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().
                addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes
                ("Bearer Authentication", createAPIKeyScheme()))
            .info(new Info().title("Matrimony REST API")
                .description("My matrimony REST BE Service")
                .version("1.0").contact(new Contact().name("Bala G")
                    .email( "www.bala.com").url("bala.in@gmail.com"))
                .license(new License().name("License of API")
                    .url("API license URL")));
    }
  
} 