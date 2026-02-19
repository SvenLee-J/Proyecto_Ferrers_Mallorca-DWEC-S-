package FerrersArtesans.com.Backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    @NotBlank  // Evita campos vacios.
    private String name;  // Nuevo nombre del usuario.

    @Size(min = 6)  // Password minimo 6 caracteres (opcional).
    private String password;  // Nueva contraseña (opcional).

    public UpdateUserRequest() {
    }

    // Constructor
    public UpdateUserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // Getters y Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
