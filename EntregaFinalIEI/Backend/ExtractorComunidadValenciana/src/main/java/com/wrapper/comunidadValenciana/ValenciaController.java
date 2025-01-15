package com.wrapper.comunidadValenciana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValenciaController {
    @Autowired
    private ValenciaService valenciaService;

    @GetMapping("/carga")
    public ResponseEntity<?> carga(){
        return new ResponseEntity<>(valenciaService.cargarDatos(), HttpStatus.OK);
    }
}
