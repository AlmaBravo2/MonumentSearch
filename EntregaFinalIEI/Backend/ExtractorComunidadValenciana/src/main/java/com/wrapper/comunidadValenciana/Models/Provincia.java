package com.wrapper.comunidadValenciana.Models;

import jakarta.persistence.*;
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
}
