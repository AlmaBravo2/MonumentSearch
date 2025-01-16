package com.wrapper.castillaYLeon.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localidad")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Localidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "provincia_codigo", nullable = false)
    private Provincia provincia;
}
