package FerrersArtesans.com.Backend.repository;

import FerrersArtesans.com.Backend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // marca como repositorio spring
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombre(String nombre); // busca categoría por nombre exacto
    List<Categoria> findAllByOrderByNombre(); // lista todas ordenadas por nombre
}
