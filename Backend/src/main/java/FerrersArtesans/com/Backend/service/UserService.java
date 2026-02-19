package FerrersArtesans.com.Backend.service;

import FerrersArtesans.com.Backend.dto.*;
import FerrersArtesans.com.Backend.model.User;
import FerrersArtesans.com.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service  // Registra como servicio de Spring.
@RequiredArgsConstructor  // Actua como autowireds y eso.
public class UserService {

    private final UserRepository userRepository;  // Acceso a base de datos.
    private final PasswordEncoder passwordEncoder;  // Encripta contraseñas.

    public UserResponse findById(Long id) {
        // Busca usuario por ID o lanza error.
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));
        return mapToUserResponse(user);  // Convierte a DTO, solo expone los datos necesarios.
    }

    public List<UserResponse> findAll() {
        // Obtiene todos los usuarios y los convierte a DTOs.
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse update(Long id, UpdateUserRequest updateRequest) {
        // Busca usuario o lanza error.
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));

        user.setNom(updateRequest.getName());  // Actualiza nombre.
        
        // Solo actualiza password si se proporciona.
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateRequest.getPassword()));  // Encripta nuevo password.
        }

        userRepository.save(user);  // Guarda cambios.
        return mapToUserResponse(user);  // Devuelve DTO actualizado.
    }

    public void delete(Long id) {
        // Verifica si existe antes de borrar.
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User no encontrado");
        }
        userRepository.deleteById(id);  // Borra usuario.
    }

    private UserResponse mapToUserResponse(User user) {
        // Convierte entidad User a DTO UserResponse.
        return new UserResponse(user.getId(), user.getEmail(), user.getNom(), user.getRol());
    }
}
