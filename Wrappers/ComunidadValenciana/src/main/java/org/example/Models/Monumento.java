package org.example.Models;


public class Monumento {
    private String nombre;
    private String tipo;
    private String direccion;
    private String codigo_postal;
    private String longitud;
    private String latitud;
    private String descripcion;
    private Localidad localidad;
    private Provincia provincia;

    public Monumento(String nombre, String tipo, String direccion, String codigo_postal, String longitud, String latitud, String descripcion, Localidad localidad, Provincia provincia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.direccion = direccion;
        this.codigo_postal = codigo_postal;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.localidad = localidad;
        this.provincia = provincia;
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

    public Provincia getProvincia() {
        return provincia;
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

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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
                ", provincia=" + provincia +
                '}';
    }


}
