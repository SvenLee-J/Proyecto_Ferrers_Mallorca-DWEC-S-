package FerrersArtesans.com.Backend.repository;

import FerrersArtesans.com.Backend.model.PerfilFerrer;
import FerrersArtesans.com.Backend.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // marca como repositorio spring
// hereda todas las operaciones crud (save, findall, delete, etc)
public interface PerfilFerrerRepository extends JpaRepository<PerfilFerrer, Long> { 
    List<PerfilFerrer> findByEstado(PerfilFerrer.EstadoFerrer estado); // lista ferrers por estado (activo/pendiente/inactivo)
    Optional<PerfilFerrer> findByUser(User user); // perfil de un usuario específico
}
