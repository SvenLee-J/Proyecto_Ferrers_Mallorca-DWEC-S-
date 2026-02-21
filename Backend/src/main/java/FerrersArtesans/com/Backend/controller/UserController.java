package FerrersArtesans.com.Backend.controller;

import FerrersArtesans.com.Backend.dto.*;
import FerrersArtesans.com.Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // Controller REST que devuelve JSON.
@RequestMapping("/api/users")  // Base URL: /api/users/**
@RequiredArgsConstructor  // Actua como autowireds y eso.
public class UserController {

    private final UserService userService;  // Servicio de usuarios.

    @GetMapping  // GET /api/users
    @PreAuthorize("hasRole('ADMIN')")  // Solo ADMIN puede ver todos.
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.findAll();  // Obtiene todos los usuarios.
        return ResponseEntity.ok(users);  // 200 OK + lista.
    }

    @GetMapping("/{id}")  // GET /api/users/{id}
    @PreAuthorize("hasRole('ADMIN') or authentication.principal == #id")  // ADMIN o propio usuario.
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.findById(id);  // Busca usuario por ID.
        return ResponseEntity.ok(user);  // 200 OK + usuario.
    }

    @PutMapping("/{id}")  // PUT /api/users/{id}
    @PreAuthorize("hasRole('ADMIN') or authentication.principal == #id")  // ADMIN o propio usuario.
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateRequest) {
        UserResponse updatedUser = userService.update(id, updateRequest);  // Actualiza usuario.
        return ResponseEntity.ok(updatedUser);  // 200 OK + usuario actualizado.
    }

    @DeleteMapping("/{id}")  // DELETE /api/users/{id}
    @PreAuthorize("hasRole('ADMIN')")  // Solo ADMIN puede borrar.
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);  // Borra usuario.
        return ResponseEntity.noContent().build();  // 204 No Content.
    }
}