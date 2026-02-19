package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.dto.*;
import FerrersArtesans.com.Backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // Controller REST que devuelve JSON.
@RequestMapping("/auth")  // Base URL: /auth/
@RequiredArgsConstructor  // Actua como autowireds y eso.
public class AuthController {

    private final AuthService authService;  // Servicio de autenticacion.

    @PostMapping("/register")  // POST /auth/register
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterRequest registerRequest) {
        MessageResponse response = authService.register(registerRequest);  // Registra usuario.
        return ResponseEntity.ok(response);  // 200 OK + mensaje.
    }

    @PostMapping("/login")  // POST /auth/login
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        JwtResponse response = authService.login(loginRequest);  // Autentica y genera JWT.
        return ResponseEntity.ok(response);  // 200 OK + token.
    }
}
