package com.wrapper.comunidadValenciana.Models;


import java.util.Objects;

public class Monumento {
    private String nombre;
    private String tipo;
    private String direccion;
    private String codigo_postal;
    private String longitud;
    private String latitud;
    private String descripcion;
    private Localidad localidad;

    public Monumento(String nombre, String tipo, String direccion, String codigo_postal, String longitud, String latitud, String descripcion, Localidad localidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.localidad = localidad;

    }

    public Monumento(){}

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigoPostal() {
        return codigo_postal;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Localidad getLocalidad() {
        return localidad;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPostal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monumento monumento = (Monumento) o;
        return Objects.equals(nombre, monumento.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }


    @Override
    public String toString() {
        return "Monumento{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigo_postal='" + codigo_postal + '\'' +
                ", longitud='" + longitud + '\'' +
                ", latitud='" + latitud + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", localidad=" + localidad +
                '}';
    }


}
