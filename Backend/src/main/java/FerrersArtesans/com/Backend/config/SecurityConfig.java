package FerrersArtesans.com.Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Definimos la clase como configuracion de spring.
@EnableWebSecurity // Activa la seguridad web de spring.

public class SecurityConfig {
    
    @Bean

    // "throws Exception indiaque que no pasa nada si falla.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        // Reglas aplicadas al http.
        http

            // Primera regla. Uso de autorizacion.
            .authorizeHttpRequests(authz -> authz // Obliga el uso de autorizacion.
                .requestMatchers("/h2-console/**").permitAll() // Perimiso a las URLs...
                .anyRequest().authenticated()  // El resto de URLs requieren login.
            )

            // Segunda regla. Proteccion anti Hakers.
            // "csrf" = proteccion contra robos de formularios.
            // "(csrf -> ...)" Configuracion de la seguridad. Ignora las URL...
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))

            
            .headers(headers -> headers .frameOptions(frameOptions -> frameOptions.sameOrigin()));

            return http.build();
    }

}
