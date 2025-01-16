package com.wrapper.Euskadi.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    public String getNombre(){return this.nombre;}
    public String getCodigoPostal(){return this.codigoPostal;}
    public String getDireccion(){return this.direccion;}
    public String getLongitud(){return this.longitud;}
    public String getLatitud(){return this.latitud;}
    public String getDescripcion(){return this.descripcion;}
    public String getTipo(){return this.tipo;}
    public void setLocalidad(Localidad localidad){this.localidad = localidad;}
public void setNombre(String nombre){this.nombre = nombre;}
public void setCodigoPostal(String codigoPostal){this.codigoPostal = codigoPostal;}
public void setDireccion(String direccion){this.direccion = direccion;}
public void setLongitud(String longitud){this.longitud = longitud;}
public void setLatitud(String latitud){this.latitud = latitud;}
public void setDescripcion(String descripcion){this.descripcion = descripcion;}
public void setTipo(String tipo){this.tipo = tipo;}

}