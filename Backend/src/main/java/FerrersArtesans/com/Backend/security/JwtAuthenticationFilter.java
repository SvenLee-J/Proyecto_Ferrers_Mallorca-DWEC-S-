package FerrersArtesans.com.Backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Identifica como bean para injeccion de dependecias.

// "extends OncePerRequestFilter" = Ejecutar una vez por cada solicitud.
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired // Inyecata dependencias automaticamente.
    private JwtTokenUtil jwtTokenUtil;

    @Autowired // Inyecata dependencias automaticamente.
    private UserDetailsService userDetailsService;

    // Constatntes para el header y el prefijo del token.
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    @Override // Indica que este metodo puede sobreescribir un metodo de la clase padre.

    // Metodo que se ejecuta para cada solicitud. Verifica el token y establece la autenticacion.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        final String authHeader = request.getHeader(HEADER); // Ontiene el header
        final String jwtToken; // Variable para el token.
        final String username; // Variable para el nombre de usuario.

        // Si no hay header o no empieza con "Bearer", pasa al siguiente filtro.
        if (authHeader == null || !authHeader.startsWith(PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extre token ( quita el "Bearer" )
        jwtToken = authHeader.substring(PREFIX.length());
        
        // Obtiene el nombre del token
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);

        // Si hay usuario y no esta autenticado.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            // Carga UserDetails
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Si el token es valido, autentifica al usuario
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                
                // Crea objecto de autenticacion con un rol
                UsernamePasswordAuthenticationToken authToken =  
                
                // "userDetails" = Usuario || "null" = No credencial (token ya valido) || "userDetails.getAuthorities" = Roles/Permisos
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // Agrega detalles de request
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // Guarda la autenticación globalmente de Spring Security.
                    SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Continua chain de filtros.
        // Sin esto la autenticacion no puede termina.
        filterChain.doFilter(request, response);
    }
}
