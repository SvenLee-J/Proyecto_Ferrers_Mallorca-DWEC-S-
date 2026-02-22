package FerrersArtesans.com.Backend.service;

import FerrersArtesans.com.Backend.model.Producto;
import FerrersArtesans.com.Backend.model.Categoria;
import FerrersArtesans.com.Backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    public List<Producto> getProductosByFerrer(String username) {
        return productoRepository.findByUserEmail(username);
    }
    
    public Producto updateProductoByFerrer(Long id, String username) {
        Producto producto = getProductoById(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        if (!producto.getUserEmail().equals(username)) {
            throw new RuntimeException("No tienes permiso para editar este producto");
        }
        return saveProducto(producto);
    }
    
    public void deleteProductoByFerrer(Long id, String username) {
        Producto producto = getProductoById(id);
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        if (!producto.getUserEmail().equals(username)) {
            throw new RuntimeException("No tienes permiso para eliminar este producto");
        }
        deleteProducto(id);
    }
    
    // Métodos existentes (mantener por ahora)
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    public List<Producto> getProductosByCategoria(Categoria categoria) {
        return productoRepository.findByCategoriaId(categoria.getId());
    }
    
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    public Producto saveProducto(Producto producto) {
        if (producto.getPrecio() == null || 
            producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Precio debe ser mayor que 0");
        }
        return productoRepository.save(producto);
    }
    
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
