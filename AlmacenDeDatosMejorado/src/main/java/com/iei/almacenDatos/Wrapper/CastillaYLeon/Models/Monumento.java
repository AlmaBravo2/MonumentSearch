package com.iei.almacenDatos.Wrapper.CastillaYLeon.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Monumento {

    //MAPEO DE LOS ELEMENTOS DEL XML A LOS DE LA CLASE JAVA
    @JacksonXmlProperty(localName = "nombre")
    private String nombre;

    @JacksonXmlProperty(localName = "tipo")
    private String tipo;

    @JacksonXmlProperty(localName = "descripcion")
    private String descripcion;

    @JacksonXmlProperty(localName = "direccion")
    private String direccion;

    @JacksonXmlProperty(localName = "codigoPostal")
    private String codigo_postal;

    @JacksonXmlProperty(localName = "provincia")
    private String provincia;

    @JacksonXmlProperty(localName = "localidad")
    private String localidad;

    @JacksonXmlProperty(localName = "latitud")
    private String latitud;

    @JacksonXmlProperty(localName = "longitud")
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

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;

    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
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

}
