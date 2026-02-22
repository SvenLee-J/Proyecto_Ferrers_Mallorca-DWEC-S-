package FerrersArtesans.com.Backend.repository;

import FerrersArtesans.com.Backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// repositorio para operaciones CRUD de productos
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    // encuentra productos por email del usuario
    List<Producto> findByUserEmail(String userEmail);
    
    // encuentra productos por categoría
    List<Producto> findByCategoriaId(Long categoriaId);
    
    // encuentra productos con stock disponible
    List<Producto> findByStockGreaterThanEqual(int stock);
}
