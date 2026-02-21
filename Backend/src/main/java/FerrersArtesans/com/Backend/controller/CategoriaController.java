package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.model.Categoria;
import FerrersArtesans.com.Backend.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // controller rest que devuelve json
@RequestMapping("/api/categorias") // base url: /api/categorias/**
@RequiredArgsConstructor // inyecta dependencias automáticamente
@PreAuthorize("hasAnyRole('CLIENT', 'FERRER', 'ADMIN')") // acceso: todos los roles
public class CategoriaController {
    
    private final CategoriaService categoriaService; // servicio de categorías

    @GetMapping // get /api/categorias
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias(); // obtiene todas las categorías
        return ResponseEntity.ok(categorias); // 200 ok + lista categorías
    }

    @GetMapping("/{id}") // get /api/categorias/{id}
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoriaById(id) // busca categoría por id
            .map(ResponseEntity::ok) // si existe → 200 ok
            .orElse(ResponseEntity.notFound().build()); // si no → 404 not found
    }
}
