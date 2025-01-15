package com.iei.apiCarga.Models;

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
}
