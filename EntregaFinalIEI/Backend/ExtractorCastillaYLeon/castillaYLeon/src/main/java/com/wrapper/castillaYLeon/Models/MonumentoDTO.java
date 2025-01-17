package com.wrapper.castillaYLeon.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MonumentoDTO {

    private String nombre;

    private Localidad localidad;

    private String tipo;

    private String descripcion;

    private String direccion;

    private String codigoPostal;

    private String latitud;

    private String longitud;


    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre; }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigo_postal) {
        this.codigoPostal = codigo_postal;

    }
    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


    public MonumentoDTO(String nombre, Localidad localidad, String tipo, String descripcion, String direccion, String codigoPostal, String latitud, String longitud) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public MonumentoDTO() {
    }
}
