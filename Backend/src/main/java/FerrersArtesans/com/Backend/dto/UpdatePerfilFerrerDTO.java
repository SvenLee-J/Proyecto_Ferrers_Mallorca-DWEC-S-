package FerrersArtesans.com.Backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

// DTO para actualizar perfil del ferrer
@Data
public class UpdatePerfilFerrerDTO {
    
    // nombre del taller (obligatorio)
    @NotBlank(message = "Nombre del taller es obligatorio")
    private String nombreTaller;
    
    // localidad del taller (obligatoria)
    @NotBlank(message = "Localidad es obligatoria")
    private String localidad;
    
    // descripción del taller (obligatoria)
    @NotBlank(message = "Descripción es obligatoria")
    private String descripcion;
    
    // teléfono de contacto (opcional)
    private String telefono;
}
