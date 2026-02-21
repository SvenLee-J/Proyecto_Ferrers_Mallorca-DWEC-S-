package FerrersArtesans.com.Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // marca clase como entidad jpa
@Table(name = "categorias") // nombre tabla en base de datos
@Data // genera getters y setters automáticamente
@NoArgsConstructor // constructor vacío requerido por jpa
@AllArgsConstructor // constructor completo con todos los campos
public class Categoria {
    
    @Id // clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // nombre obligatorio y único
    private String nombre;

    @Column(length = 200) // descripción opcional máx 200 chars
    private String descripcion;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 1:many productos
    private List<Producto> productos = new ArrayList<>(); // lista productos de esta categoría
}
