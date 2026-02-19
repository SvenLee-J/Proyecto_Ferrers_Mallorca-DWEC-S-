package FerrersArtesans.com.Backend.repository;

import FerrersArtesans.com.Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // "extend JpaRepository" Obtiene las operaciones CRUD automaticamente.
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Busca un usuario por su email, "Optional" indica que puede tener un valor o no.
    Optional<User> findByEmail(String email);
    // Verifica so existe un usario con el emial.
    boolean existsByEmail(String email);
}
