package FerrersArtesans.com.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity // marca clase como entidad jpa
@Table(name = "productos") // nombre tabla en base de datos
@Data // genera getters y setters automáticamente
@NoArgsConstructor // constructor vacío requerido por jpa
@AllArgsConstructor // constructor completo con todos los campos
public class Producto {
    
    @Id // clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private Long id;

    @Column(nullable = false, length = 100) // nombre obligatorio máx 100 chars
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2) // precio obligatorio 8 enteros 2 decimales
    private BigDecimal precio;

    @Column(nullable = false) // stock obligatorio
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY) // many:1 con categoría (carga perezosa)
    @JoinColumn(name = "categoria_id", nullable = false) // clave foránea categoría_id obligatoria
    private Categoria categoria;
}
