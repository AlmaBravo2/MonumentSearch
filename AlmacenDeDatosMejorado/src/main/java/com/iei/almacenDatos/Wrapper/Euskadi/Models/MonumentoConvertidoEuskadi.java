package com.iei.almacenDatos.Wrapper.Euskadi.Models;

public class MonumentoConvertidoEuskadi {
    private String nombre;
    private String tipo;
    private String direccion;
    private String codigo_postal;
    private String latitud;
    private String  longitud;
    private String descripcion;
    private String provincia;
    private String localidad;



    public MonumentoConvertidoEuskadi(String nombre, String tipo, String direccion, String codigoPostal, String latitud, String longitud, String descripcion, String provincia, String localidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.direccion = direccion;
        this.codigo_postal = codigoPostal;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.localidad = localidad;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
}
