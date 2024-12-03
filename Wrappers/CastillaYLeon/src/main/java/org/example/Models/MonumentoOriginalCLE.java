package org.example.Models;


public class MonumentoOriginalCLE {

    private String nombre;
    private String tipoMonumento;
    private String calle;
    private String codigoPostal;
    private String coordenadas;
    private String latitud;
    private String longitud;
    private String descripcion;
    private String localidad;
    private String provincia;
    private String clasificacion;



    private String municipio;

    public MonumentoOriginalCLE(){
        this.nombre = null;
        this.tipoMonumento = null;
        this.calle = null;
        this.codigoPostal = null;
        this.coordenadas = null;
        this.latitud = null;
        this.longitud = null;
        this.descripcion = null;
        this.localidad = null;
        this.provincia = null;
        this.clasificacion = null;
    }

    public MonumentoOriginalCLE(String nombre, String tipoMonumento, String calle,
                                String codigoPostal, String coordenadas, String latitud,
                                String longitud, String descripcion, String localidad,
                                String provincia)
    {

        this.nombre = nombre;
        this.tipoMonumento = tipoMonumento;
        this.calle = calle;
        this.codigoPostal = codigoPostal;
        this.coordenadas = coordenadas;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
        this.localidad = localidad;
        this.provincia = provincia;
        this.clasificacion = clasificacion;

    }

    public String getNombre(){ return nombre; }
    public String getTipoMonumento(){ return tipoMonumento; }
    public String getCalle(){ return calle; }
    public String getCodigoPostal(){ return codigoPostal; }
    public String getCoordenadas(){return coordenadas; }
    public String getLatitud(){ return latitud; }
    public String getLongitud(){ return longitud; }
    public String getDescripcion(){ return descripcion; }
    public String getLocalidad(){ return localidad; }
    public String getClasificacion(){ return clasificacion; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoMonumento(String tipoMonumento) {
        this.tipoMonumento = tipoMonumento;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}