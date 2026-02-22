package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.model.Producto;
import FerrersArtesans.com.Backend.service.ProductoService;
import FerrersArtesans.com.Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// controlador REST para productos del ferrer
@RestController
@RequestMapping("/api/ferrers/productos")
@RequiredArgsConstructor
public class ProductoController {
    
    // servicios inyectados
    private final ProductoService productoService;
    private final UserService userService;

    // obtiene productos del ferrer autenticado
    @GetMapping
    @PreAuthorize("hasRole('FERRER')")
    public ResponseEntity<List<Producto>> getMisProductos(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(productoService.getProductosByFerrer(username));
    }

    // crea nuevo producto para ferrer
    @PostMapping
    @PreAuthorize("hasRole('FERRER')")
    public ResponseEntity<Producto> createProducto(
            @RequestBody Producto producto, 
            Authentication auth) {
        String username = auth.getName();
        producto.setUserEmail(username);
        return ResponseEntity.ok(productoService.saveProducto(producto));
    }

    // actualiza producto del ferrer
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('FERRER')")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable Long id, 
            @RequestBody Producto productoUpdate, 
            Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(productoService.updateProductoByFerrer(id, username));
    }

    // elimina producto del ferrer
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('FERRER')")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();
        productoService.deleteProductoByFerrer(id, username);
        return ResponseEntity.ok().build();
    }
}
