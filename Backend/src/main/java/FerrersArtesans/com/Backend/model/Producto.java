package FerrersArtesans.com.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// entidad para productos en base de datos
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    
    // clave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nombre del producto
    @Column(nullable = false, length = 100)
    private String nombre;

    // precio con precisión decimal
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    // cantidad en stock
    @Column(nullable = false)
    private Integer stock;

    // email del propietario ferrer
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    // estado de disponibilidad
    @Column(name = "disponible")
    private Boolean disponible = true;

    // relación con categoría
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}
