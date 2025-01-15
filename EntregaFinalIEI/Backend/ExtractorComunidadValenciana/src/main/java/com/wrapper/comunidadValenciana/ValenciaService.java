package com.wrapper.comunidadValenciana;

import com.wrapper.comunidadValenciana.Logic.Convertidor;
import com.wrapper.comunidadValenciana.Models.Monumento;
import com.wrapper.comunidadValenciana.Models.MonumentosDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValenciaService {

    public MonumentosDTO cargarDatos(){
        return Convertidor.getMonumentos("src/main/java/com/wrapper/comunidadValenciana/DataSource/bienes_inmuebles_interes_cultural.csv");
    }
}
