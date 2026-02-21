package FerrersArtesans.com.Backend.service;

import FerrersArtesans.com.Backend.model.Producto;
import FerrersArtesans.com.Backend.model.Categoria;
import FerrersArtesans.com.Backend.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    
    private final ProductoRepository productoRepository;
    
    // Todos los productos disponibles
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
    // Productos por categoría (Herramientas, Materiales)
    public List<Producto> getProductosByCategoria(Categoria categoria) {
        return productoRepository.findByCategoriaId(categoria.getId());
    }
    
    // Productos con stock (para tienda)
    public List<Producto> getProductosEnStock(int stockMinimo) {
        return productoRepository.findByStockGreaterThanEqual(stockMinimo);
    }
    
    // Producto por ID
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    
    // Crea/actualiza producto
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    
    // Elimina producto
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
