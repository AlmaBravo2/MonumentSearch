package iei.proyecto.monumentos.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Provincia")
public class Provincia {

    @Id
    @Column(name = "codigo")
    private int codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    public Provincia() {}

    public Provincia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
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
}
