package FerrersArtesans.com.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // marca clase como entidad jpa
@Table(name = "perfiles_ferrers") // nombre tabla en base de datos
@Data // genera getters y setters automáticamente
@NoArgsConstructor // constructor vacío requerido por jpa
@AllArgsConstructor // constructor completo con todos los campos
public class PerfilFerrer {
    
    @Id // clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // relación 1:1 con user (carga perezosa)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // clave foránea user_id
    private User user;

    @Column(name = "nombre_completo", nullable = false, length = 100) // campo obligatorio máx 100 chars
    private String nombreCompleto;

    @Column(name = "telefono", length = 20) // teléfono opcional máx 20 chars
    private String telefono;

    @Column(name = "especialidad", length = 100) // especialidad opcional máx 100 chars
    private String especialidad;

    @Enumerated(EnumType.STRING) // guarda enum como string en bd
    @Column(name = "estado", nullable = false) // estado obligatorio
    private EstadoFerrer estado = EstadoFerrer.PENDIENTE; // valor por defecto pendiente

    public enum EstadoFerrer { // enumeración estados ferrer
        ACTIVO, PENDIENTE, INACTIVO
    }
}
