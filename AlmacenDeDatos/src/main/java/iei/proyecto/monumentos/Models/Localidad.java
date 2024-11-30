package iei.proyecto.monumentos.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Localidad")
public class Localidad {

    @Id
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_codigo", nullable = false)
    private Provincia provincia;

    public Localidad() {}

    public Localidad(int codigo, String nombre, Provincia provincia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.provincia = provincia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
