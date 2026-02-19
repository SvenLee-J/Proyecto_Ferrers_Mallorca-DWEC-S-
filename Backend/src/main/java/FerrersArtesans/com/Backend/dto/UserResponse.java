package FerrersArtesans.com.Backend.dto;

public class UserResponse {
    private Long id;     // ID del usuario.
    private String email; // Email del usuario.
    private String name;  // Nombre del usuario.
    private String role;  // Rol del usuario.

    // Constructor
    public UserResponse(Long id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
