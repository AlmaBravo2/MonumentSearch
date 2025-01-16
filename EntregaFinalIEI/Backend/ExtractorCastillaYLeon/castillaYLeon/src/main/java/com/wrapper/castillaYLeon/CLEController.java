package com.wrapper.castillaYLeon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CLEController {

    @Autowired
    private CLEService cleService;

    @GetMapping("/carga")
    public ResponseEntity<?> carga(){ return new ResponseEntity<>(cleService.cargarDatosCLE(),HttpStatus.OK);
    }
}
