package FerrersArtesans.com.Backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration  // Clase de configuracion de Spring.
@EnableWebSecurity  // Activa seguridad web de Spring Security.
@EnableMethodSecurity  // Permite usar @PreAuthorize en los metodos.
public class SecurityConfig {

    @Autowired  // Inyecta dependencias automaticamente.
    private CustomUserDetailsService userDetailsService;  // Servicio de usuarios personalizado.

    @Autowired  // Inyecta dependencias automaticamente.
    private JwtAuthenticationFilter jwtAuthenticationFilter;  // Filtro JWT personalizado.

    @Bean  // Crea bean para encriptar contraseñas.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt = algoritmo fuerte para passwords.
    }

    @Bean  // Necesario para el login (POST /auth/login).
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();  
    }

    @Bean  // Cadena principal de filtros de seguridad.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desactiva CSRF (JWT no necesita cockies).
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sin sesiones (JWT stateless).
            .authorizeHttpRequests(authz -> authz  // Reglas de acceso.
                .requestMatchers("/auth/**", "/h2-console/**").permitAll()  // Login y H2 libres.
                .anyRequest().authenticated()  // TODO lo demas REQUIERE token JWT.
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Agrega filtro JWT ANTES del filtro normal.
            .cors(cors -> cors.configurationSource(corsConfigurationSource()));  // Habilita CORS para Angular.

        return http.build();
    }

    @Bean  // Configuracion CORS para frontend.
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));  // Permite cualquier origen (frontend).
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Metodos HTTP permitidos.
        configuration.setAllowedHeaders(Arrays.asList("*"));  // Headers permitidos (Authorization).
        configuration.setAllowCredentials(true);  // Permite cookies si las usas.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Aplica a todas las rutas.
        return source;
    }
}
