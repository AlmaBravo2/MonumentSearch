package com.iei.apiBusqueda.Models;
/**
 * En esta clase se define la entidad Localidad, tal como fue especificada en el esquema global para la primera entrega del proyecto de prácticas.
 * @author M12.01
 * @version 1.0
 * */
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localidad")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Localidad {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_codigo", nullable = false)
    private Provincia provincia;


    public String getNombre(){return this.nombre;}


    public Provincia getProvincia(){return this.provincia;}

}
