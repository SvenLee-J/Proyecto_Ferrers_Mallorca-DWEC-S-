package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.dto.UpdatePerfilFerrerDTO;
import FerrersArtesans.com.Backend.model.PerfilFerrer;
import FerrersArtesans.com.Backend.model.User;
import FerrersArtesans.com.Backend.service.PerfilFerrerService;
import FerrersArtesans.com.Backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

// controlador REST para perfiles de ferrer
@RestController
@RequestMapping("/api/ferrers")
@RequiredArgsConstructor
public class FerrerController {
    
    // servicios inyectados
    private final PerfilFerrerService perfilFerrerService;
    private final UserService userService;

    // actualiza perfil del ferrer autenticado
    @PutMapping("/perfil")
    @PreAuthorize("hasRole('FERRER')")
    public ResponseEntity<PerfilFerrer> updateMiPerfil(
            @Valid @RequestBody UpdatePerfilFerrerDTO dto,
            Authentication auth) {
        String username = auth.getName();
        User user = userService.findByUsername(username).orElseThrow();
        PerfilFerrer perfil = perfilFerrerService.findByUser(user)
            .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        perfil.setNombreCompleto(dto.getNombreTaller());
        perfil.setEspecialidad(dto.getLocalidad() + " - " + dto.getDescripcion());
        perfil.setTelefono(dto.getTelefono());
        
        return ResponseEntity.ok(perfilFerrerService.savePerfilFerrer(perfil));
    }
}
