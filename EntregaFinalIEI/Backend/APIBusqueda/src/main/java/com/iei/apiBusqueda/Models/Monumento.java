package com.iei.apiBusqueda.Models;
/**
 * En esta clase se define la entidad Monumento, tal como fue especificada en el esquema global para la primera entrega del proyecto de prácticas.
 * @author M12.01
 * @version 1.0
 * */
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
@Entity
@Table(name = "monumento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Check(constraints = "tipo IN ('Yacimiento arqueológico','Iglesia-Ermita','Monasterio-Convento','Castillo-Fortaleza-Torre','Edificio singular','Puente','Otros')")
public class Monumento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "direccion", length = 1000, nullable = false)
    private String direccion;

    @Column(name = "codigo_postal", length = 5, nullable = false)
    private String codigoPostal;

    @Column(name = "longitud", nullable = false)
    private String longitud;

    @Column(name = "latitud", nullable = false)
    private String latitud;

    @Column(name = "descripcion", length = 1000, nullable = false)
    private String descripcion;

    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "localidad_id", nullable = false)
    private Localidad localidad;


    public Localidad getLocalidad(){return this.localidad;}
    public String getCodigoPostal(){return this.codigoPostal;}

    public String getTipo(){return this.tipo;}

}
