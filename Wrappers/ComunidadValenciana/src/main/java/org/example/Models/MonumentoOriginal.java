package org.example.Models;

public class MonumentoOriginal {
    //IGPCV;DENOMINACION;PROVINCIA;MUNICIPIO;UTMESTE;UTMNORTE;CODCLASIFICACION;CLASIFICACION;CODCATEGORIA;CATEGORIA
    private String igpcv;
    private String denominacion;
    private String provincia;
    private String municipio;
    private String utmeste;
    private String utmnorte;
    private String codclasificacion;
    private String clasificacion;
    private String codcategoria;
    private String categoria;

    public MonumentoOriginal(String igpcv, String denominacion, String provincia, String municipio, String utmeste, String utmnorte, String codclasificacion, String clasificacion, String codcategoria, String categoria) {
        this.igpcv = igpcv;
        this.denominacion = denominacion;
        this.provincia = provincia;
        this.municipio = municipio;
        this.utmeste = utmeste;
        this.utmnorte = utmnorte;
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

    public String getUtmeste() {
        return utmeste;
    }

    public String getUtmnorte() {
        return utmnorte;
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
