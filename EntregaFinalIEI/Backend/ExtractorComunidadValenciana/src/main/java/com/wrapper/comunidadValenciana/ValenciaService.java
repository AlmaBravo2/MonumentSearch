package com.wrapper.comunidadValenciana;

import com.wrapper.comunidadValenciana.Models.MonumentosDTO;
import org.springframework.stereotype.Service;

@Service
public class ValenciaService {

    public MonumentosDTO cargarDatos(){
        Convertidor convertidor = new Convertidor();
        return convertidor.getMonumentos("src/main/java/com/wrapper/comunidadValenciana/DataSource/bienes_inmuebles_interes_cultural.csv");
    }
}
