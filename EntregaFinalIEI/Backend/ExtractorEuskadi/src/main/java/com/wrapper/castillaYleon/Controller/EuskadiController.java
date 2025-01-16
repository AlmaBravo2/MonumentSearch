package com.wrapper.castillaYleon.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EuskadiController {

    @GetMapping("/carga")
    public ResponseEntity<?> carga(){
        return new ResponseEntity<>("Carga de datos de Euskadi", HttpStatus.OK);
    }

}
