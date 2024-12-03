package org.example.Utils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Monumento {


    @JacksonXmlProperty(localName = "nombre")
    private String nombre;

    @JacksonXmlProperty(localName = "tipoMonumento")
    private String tipoMonumento;

    @JacksonXmlProperty(localName = "descripcion")
    private String descripcion;

    @JacksonXmlProperty(localName = "direccion")
    private String direccion;

    @JacksonXmlProperty(localName = "codigoPostal")
    private String codigoPostal;

    @JacksonXmlProperty(localName = "provincia")
    private String provincia;

    @JacksonXmlProperty(localName = "municipio")
    private String municipio;

    @JacksonXmlProperty(localName = "localidad")
    private String localidad;

    @JacksonXmlProperty(localName = "latitud")
    private String latitud;

    @JacksonXmlProperty(localName = "longitud")
    private String longitud;

    /*@JacksonXmlProperty(localName = "poblacion")
    private Poblacion poblacion;

    @JacksonXmlProperty(localName = "coordenadas")
    private Coordenadas coordenadas;*/

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre; }

    public String getTipoMonumento() {
        return tipoMonumento;
    }

    public void setTipoMonumento(String tipoMonumento) {
        this.tipoMonumento = tipoMonumento;
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

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;

    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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

   /* public Poblacion getPoblacion() { return poblacion; }

    public void setPoblacion(Poblacion poblacion) { this.poblacion = poblacion; }

    public Coordenadas getCoordenadas() { return coordenadas; }

    public void setCoordenadas(Coordenadas coordenadas) { this.coordenadas = coordenadas; }
*/
/*public static class Coordenadas {
    @JacksonXmlProperty(localName = "latitud")
    private String latitud;

    @JacksonXmlProperty(localName = "longitud")
    private String longitud;

    // Getters y setters
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

public static class Poblacion {
        @JacksonXmlProperty(localName = "provincia")
        private String provincia;

        @JacksonXmlProperty(localName = "municipio")
        private String municipio;

        @JacksonXmlProperty(localName = "localidad")
        private String localidad;

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

        public String getMunicipio() {
            return municipio;
        }

        public void setMunicipio(String municipio) {
            this.municipio = municipio;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

    }
*/
}
