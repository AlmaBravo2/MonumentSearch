package com.wrapper.Euskadi.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "provincia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    public String getNombre(){return this.nombre;}
    public Integer getCodigo(){return this.codigo;}
    public void setCodigo(Integer code){
        this.codigo = code;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
