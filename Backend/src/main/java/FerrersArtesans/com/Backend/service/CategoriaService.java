package FerrersArtesans.com.Backend.service;

import FerrersArtesans.com.Backend.model.Categoria;
import FerrersArtesans.com.Backend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    // Lista todas las categorías ordenadas por nombre
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAllByOrderByNombre();
    }
    
    // Busca categoría por ID
    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }
    
    // Busca por nombre exacto (Herramientas, Materiales)
    public Optional<Categoria> getCategoriaByNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre);
    }
    
    // Crea/actualiza categoría
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    // Elimina categoría por ID
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
