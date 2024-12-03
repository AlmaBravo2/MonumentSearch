package com.iei.almacenDatos.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonumentoDTO {
    private String nombre;
    private String tipo;
    private String direccion;
    private String codigo_postal;
    private String longitud;
    private String latitud;
    private String descripcion;
    private String localidad;
    private String provincia;
}
