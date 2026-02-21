package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.model.PerfilFerrer;
import FerrersArtesans.com.Backend.service.PerfilFerrerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // controller rest que devuelve json
@RequestMapping("/api/ferrers") // base url: /api/ferrers/**
@RequiredArgsConstructor // inyecta dependencias automáticamente
public class FerrerController {
    
    private final PerfilFerrerService perfilFerrerService; // servicio de perfiles ferrer

    @GetMapping("/perfiles") // get /api/ferrers/perfiles
    @PreAuthorize("hasRole('FERRER') or hasRole('ADMIN')") // solo ferrer o admin
    public ResponseEntity<List<PerfilFerrer>> getFerrersPerfiles() {
        List<PerfilFerrer> perfiles = perfilFerrerService.getFerrersActivos(); // obtiene ferrers activos
        return ResponseEntity.ok(perfiles); // 200 ok + lista perfiles
    }

    @GetMapping // get /api/ferrers
    @PreAuthorize("hasRole('ADMIN')") // solo admin
    public ResponseEntity<List<PerfilFerrer>> getAllFerrers() {
        List<PerfilFerrer> ferrers = perfilFerrerService.getAllFerrers(); // obtiene todos los ferrers
        return ResponseEntity.ok(ferrers); // 200 ok + lista completa
    }
}
