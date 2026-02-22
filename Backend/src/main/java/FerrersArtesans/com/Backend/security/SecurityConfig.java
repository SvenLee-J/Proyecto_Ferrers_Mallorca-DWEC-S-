package FerrersArtesans.com.Backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Bean // cadena principal de filtros de seguridad
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desactiva csrf (jwt no usa cookies)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // sesiones stateless (sin server sessions)
            .authorizeHttpRequests(authz -> authz // reglas de autorización por endpoint
                
                // SWAGGER
                .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/v3/api-docs", "/v3/api-docs.yaml", "/webjars/**", "/swagger-resources/**").permitAll()
                
                // público: registro, login y h2 console
                .requestMatchers("/auth/register", "/auth/login", "/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/users").hasAnyRole("CLIENT", "FERRER", "ADMIN") // users: todos los roles
                .requestMatchers(HttpMethod.GET, "/api/ferrers").hasAnyRole("FERRER", "ADMIN") // ferrers: solo herreros + admin
                .requestMatchers(HttpMethod.GET, "/api/categorias/**").hasAnyRole("CLIENT", "FERRER", "ADMIN") // categorías: todos
                .requestMatchers(HttpMethod.GET, "/api/productos/**").hasAnyRole("CLIENT", "FERRER", "ADMIN") // productos: todos
                .requestMatchers(HttpMethod.GET, "/api/ferrers/perfiles/**").hasAnyRole("FERRER", "ADMIN") // perfiles ferrer: herreros + admin
                .anyRequest().authenticated() // resto endpoints requieren jwt válido
            )
            .headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.disable()) // permite iframes (h2-console)
            )
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // habilita cors para frontend angular
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // filtro JWT ANTES de username/password

        return http.build(); // construye y activa cadena de filtros
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