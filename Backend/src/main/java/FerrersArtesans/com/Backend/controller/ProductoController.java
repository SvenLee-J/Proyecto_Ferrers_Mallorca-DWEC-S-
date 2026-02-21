package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.model.Producto;
import FerrersArtesans.com.Backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // controller rest que devuelve json
@RequestMapping("/api/productos") // base url: /api/productos/**
@RequiredArgsConstructor // inyecta dependencias automáticamente
@PreAuthorize("hasAnyRole('CLIENT', 'FERRER', 'ADMIN')") // acceso: todos los roles
public class ProductoController {
    
    private final ProductoService productoService; // servicio de productos

    @GetMapping // get /api/productos
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos(); // obtiene todos los productos
        return ResponseEntity.ok(productos); // 200 ok + lista productos
    }

    @GetMapping("/categoria/{categoriaId}") // get /api/productos/categoria/{id}
    public ResponseEntity<List<Producto>> getProductosByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.getAllProductos()); // devuelve todos (lógica simplificada)
    }

    @GetMapping("/{id}") // get /api/productos/{id}
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id); // busca producto por id
        return producto != null ? // si existe
            ResponseEntity.ok(producto) : // 200 ok + producto
            ResponseEntity.notFound().build(); // si no → 404 not found
    }
}
