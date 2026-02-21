package FerrersArtesans.com.Backend.service;

import FerrersArtesans.com.Backend.model.PerfilFerrer;
import FerrersArtesans.com.Backend.model.User;
import FerrersArtesans.com.Backend.repository.PerfilFerrerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PerfilFerrerService {
    
    private final PerfilFerrerRepository perfilFerrerRepository;
    
    // Lista TODOS los ferrers
    public List<PerfilFerrer> getAllFerrers() {
        return perfilFerrerRepository.findAll();
    }
    
    // Ferrer por ID
    public Optional<PerfilFerrer> getFerrerById(Long id) {
        return perfilFerrerRepository.findById(id);
    }
    
    // Solo FERRERS ACTIVOS (para dashboard)
    public List<PerfilFerrer> getFerrersActivos() {
        return perfilFerrerRepository.findByEstado(PerfilFerrer.EstadoFerrer.ACTIVO);
    }
    
    // Crea/actualiza perfil ferrer
    public PerfilFerrer savePerfilFerrer(PerfilFerrer perfilFerrer) {
        return perfilFerrerRepository.save(perfilFerrer);
    }
    
    // Elimina ferrer
    public void deletePerfilFerrer(Long id) {
        perfilFerrerRepository.deleteById(id);
    }
    
    // Busca perfil por USER
    public Optional<PerfilFerrer> findByUser(User user) {
        return perfilFerrerRepository.findByUser(user);
    }
}
