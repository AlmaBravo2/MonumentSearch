package org.example;

public class MonumentoConvertido {
    private String igpcv;
    private String denominacion;
    private String provincia;
    private String municipio;
    private String latitud;
    private String longitud;
    private String codclasificacion;
    private String clasificacion;
    private String codcategoria;
    private String categoria;

    public MonumentoConvertido(String igpcv, String denominacion, String provincia, String municipio, String latitud, String longitud, String codclasificacion, String clasificacion, String codcategoria, String categoria) {
        this.igpcv = igpcv;
        this.denominacion = denominacion;
        this.provincia = provincia;
        this.municipio = municipio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.codclasificacion = codclasificacion;
        this.clasificacion = clasificacion;
        this.codcategoria = codcategoria;
        this.categoria = categoria;
    }

    public String getIgpcv() {
        return igpcv;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public String getCodclasificacion() {
        return codclasificacion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public String getCodcategoria() {
        return codcategoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
