package ec.edu.ups.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_PRODUCTOS")
public class ProductosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", unique = true)
    private String Nombre;

    @Column(name = "Descripcion")
    private String descripcion;

}
