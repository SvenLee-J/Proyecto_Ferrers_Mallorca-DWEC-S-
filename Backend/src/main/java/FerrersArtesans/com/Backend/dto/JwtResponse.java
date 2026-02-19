package FerrersArtesans.com.Backend.dto;

public class JwtResponse {
    private String token; // Token generado
    private String type = "Bearer"; // Tipo de token
    private String email; // Email de user autenticado
    private String role; // Rol de user autenticado

    // Contructor
    public JwtResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
