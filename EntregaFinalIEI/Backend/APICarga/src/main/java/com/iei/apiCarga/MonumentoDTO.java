package com.iei.apiCarga;

import lombok.Getter;

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
