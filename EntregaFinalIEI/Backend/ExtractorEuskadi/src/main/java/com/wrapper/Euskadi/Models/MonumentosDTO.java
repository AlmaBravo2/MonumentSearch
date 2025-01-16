package com.wrapper.Euskadi.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonumentosDTO {
    private List<Monumento> monumentos;
    private String informe;

    public List<Monumento> getMonumentos(){return this.monumentos;}
    public String getInforme(){return this.informe;}
    public void setMonumentos(List<Monumento> monumentos){this.monumentos = monumentos;}
    public void setInforme(String informe){this.informe = informe;}
    public String toString(){
        return "MonumentosDTO{" +
                "monumentos=" + monumentos +
                ", informe='" + informe + '\'' +
                '}';
    }
}
