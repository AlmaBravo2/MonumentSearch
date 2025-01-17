package com.wrapper.castillaYLeon.Models;

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
    private List<MonumentoDTO> monumentos;
    private String informe;
}
