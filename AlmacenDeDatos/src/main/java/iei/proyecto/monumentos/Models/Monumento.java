package iei.proyecto.monumentos.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Monumento")
public class Monumento {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @Column(name = "codigo_postal", nullable = false)
    private int codigoPostal;

    @Column(name = "longitud", nullable = false)
    private double longitud;

    @Column(name = "latitud", nullable = false)
    private double latitud;

    @Column(name = "descripcion", length = 40, nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 50)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "localidad_id", nullable = false)
    private Localidad localidad;

    // Constructor vacío
    public Monumento() {}

    public Monumento(int id, String nombre, String direccion, int codigoPostal, double longitud, double latitud,
                     String descripcion, Tipo tipo, Localidad localidad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.longitud = longitud;
        this.latitud = latitud;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
}
