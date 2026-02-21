package FerrersArtesans.com.Backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

@Component // Identifica como bean para injeccion de dependecias.
public class JwtTokenUtil {

    // Clave secreta Obnida del application.properties.
    @Value("${jwt.secret}")
    private String secret;

    // Tiempo de expiracion desde el application.properties.
    @Value("${jwt.expiration}")
    private Long expiration;

    // Obtiene el nombre de usuario del token.
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Obtiene la fecha de expiracion del token.
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Extrae los claims del token.
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Obtiene los datos del token.
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Obtiene la clave de firma a partir de la clave secreta.
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Verifica si el token ha expirado.
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Genera un token para el usuario.
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Extrae el rol del token jwt.
        List<String> roles = userDetails.getAuthorities().stream()
            .map(auth -> auth.getAuthority().replace("ROLE", "")) // Quita el prefijo del token.
            .collect(Collectors.toList()); // Convierte a lista string
        claims.put("roles", roles); // Añade roles al jwt

        return createToken(claims, userDetails.getUsername());
    }

    // Crea el token.
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // Agrega los claims al token.
                .setSubject(subject) // Establece el nombre de usuario del token.
                .setIssuedAt(new Date(System.currentTimeMillis())) // Establece la fecha de inicio del token.
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)) // Establece la fecha.
                .signWith(getSigningKey()) // Firma el token con la clave secreta y el algoritmo de firma.
                .compact(); // Compila el token y lo devuelve como cadena de texto.
    }

    // Valida el token verificando el nombre de usuario y la expiracion.
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token); 
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
