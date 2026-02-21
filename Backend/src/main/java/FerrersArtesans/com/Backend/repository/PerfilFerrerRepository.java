package FerrersArtesans.com.Backend.repository;

import FerrersArtesans.com.Backend.model.PerfilFerrer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // marca como repositorio spring

// hereda todas las operaciones crud (save, findall, delete, etc)
public interface PerfilFerrerRepository extends JpaRepository<PerfilFerrer, Long> { 
}
