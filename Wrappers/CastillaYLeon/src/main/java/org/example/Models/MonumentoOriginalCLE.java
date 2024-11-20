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



    public MonumentoOriginalCLE(String nombre, String tipoMonumento, String calle,
                                String codigoPostal, String coordenadas, String latitud,
                                String longitud, String descripcion, String localidad,
                                String provincia, String clasificacion)
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
}