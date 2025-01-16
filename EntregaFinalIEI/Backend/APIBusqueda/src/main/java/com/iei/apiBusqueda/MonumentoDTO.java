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

    public MonumentoDTO(Monumento monumento, Localidad localidad){
        this.monumento = monumento;
        this.localidad = localidad;

    }
}
