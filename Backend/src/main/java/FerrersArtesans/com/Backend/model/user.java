package FerrersArtesans.com.Backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indica a Spring que es una entidad.
@Table (name = "users") // Nombre de la tabla en BD.
@Data // Genera Getters y Setters.
@NoArgsConstructor // Nos evita crear Constructores Vacios.
@AllArgsConstructor // Genera los constructores automaticamente.

public class User {
   
    @Id // Indica que este atributo es un id.
    // Genera un valor de tipo ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Solo debe haber un mismo nombre y es el campo es obligatorio.
    @Column(unique = true, nullable = false)
    private String nom;
    
    // Este campo es obligatorio.
    @Column(nullable = false)
    private String password;
    
    // Este campo es obligatorio.
    @Column(nullable = false)
    private String email;

    // Este campo es obligatorio.
    @Column(nullable = false)
    private String rol;
}
