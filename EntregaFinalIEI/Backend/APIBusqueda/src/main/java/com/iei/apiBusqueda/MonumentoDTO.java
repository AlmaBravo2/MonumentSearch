package com.iei.apiBusqueda;

import com.iei.apiBusqueda.Models.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class MonumentoDTO {
    private Monumento monumento;
    private Localidad localidad;
    private Provincia provincia;

    public MonumentoDTO(Monumento monumento, Localidad localidad, Provincia provincia){

        this.monumento = monumento;
        this.localidad = localidad;
        this.provincia = provincia;


    }
}
