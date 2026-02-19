package FerrersArtesans.com.Backend.dto;

public class MessageResponse {
    private String message; // Mensaje de respuesta

    // Connstructor
    public MessageResponse(String message) {
        this.message = message;
    }

    // Getters y Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
