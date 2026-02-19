package FerrersArtesans.com.Backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank  // Evita campos vacios.
    @Email     // Indica que el formato debe ser Email.
    private String email;

    @NotBlank  // Evita campos vacios.
    private String password;

    public LoginRequest() {
    }

    // Constructor
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
