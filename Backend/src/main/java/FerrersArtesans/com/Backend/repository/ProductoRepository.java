package FerrersArtesans.com.Backend.repository;

import FerrersArtesans.com.Backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // marca como repositorio spring
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoriaId(Long categoriaId); // productos de una categoría específica
    List<Producto> findByStockGreaterThanEqual(int stock); // productos con stock mínimo dado
}
