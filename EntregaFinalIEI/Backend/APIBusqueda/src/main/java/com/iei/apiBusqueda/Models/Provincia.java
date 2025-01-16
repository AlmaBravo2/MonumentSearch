package com.iei.apiBusqueda.Models;
/**
 * En esta clase se define la entidad Provincia, tal como fue especificada en el esquema global para la primera entrega del proyecto de prácticas.
 * @author M12.01
 * @version 1.0
 * */
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provincia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;


    public String getNombre(){return this.nombre;}
}
