package FerrersArtesans.com.Backend.service;

import FerrersArtesans.com.Backend.dto.*;
import FerrersArtesans.com.Backend.model.User;
import FerrersArtesans.com.Backend.repository.UserRepository;
import FerrersArtesans.com.Backend.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service  // Registra como servicio de Spring.
@RequiredArgsConstructor // Actua como autowireds y eso.
public class AuthService {

    private final UserRepository userRepository;       // Acceso a base de datos.
    private final PasswordEncoder passwordEncoder;     // Encripta contraseñas.
    private final JwtTokenUtil jwtTokenUtil;           // Genera tokens JWT.
    private final AuthenticationManager authenticationManager;  // Valida login.

    public MessageResponse register(RegisterRequest registerRequest) {
        // Verifica si email ya existe.
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        // Crea nuevo usuario.
        User user = new User();
        user.setEmail(registerRequest.getEmail());           // Asigna email.
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));  // Encripta y asigna password.
        user.setNom(registerRequest.getName());              // Asigna nombre.
        user.setRol(registerRequest.getRole());              // Asigna rol.

        userRepository.save(user);  // Guarda en base de datos.
        return new MessageResponse("User registered successfully!");
    }

    public JwtResponse login(LoginRequest loginRequest) {
        // Autentica usuario con Spring Security.
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        // Variable usada para generar Token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        // Genera token JWT.
        String jwt = jwtTokenUtil.generateToken(userDetails);
        
        // Obtiene datos del usuario.
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        return new JwtResponse(jwt, user.getEmail(), user.getRol());
    }
}
