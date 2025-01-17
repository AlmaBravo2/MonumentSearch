package org.example.Models;


public class Monumento {
    private String nombre;
    private String tipo;
    private String direccion;
    private String codigo_postal;
    private String longitud;
    private String latitud;
    private String descripcion;
    private String localidad;
    private String provincia;


    public Monumento(String nombre, String tipo, String direccion, String codigo_postal, String longitud, String latitud, String descripcion, String localidad, String provincia) {
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

    public Monumento() {
        this.nombre = "";
        this.tipo = "";
        this.direccion = "";
        this.codigo_postal = "";
        this.longitud = "";
        this.latitud = "";
        this.descripcion = "";
        this.localidad = "";
        this.provincia = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
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
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }


}
