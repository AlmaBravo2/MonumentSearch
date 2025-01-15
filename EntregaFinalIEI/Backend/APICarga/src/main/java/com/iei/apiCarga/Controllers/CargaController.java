package com.iei.apiCarga.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CargaController {

    @PostMapping("/carga/")
    public String cargarDatos(@RequestParam(name= "todos", defaultValue = "false") boolean todos,
                              @RequestParam(name= "cv", defaultValue = "false") boolean cv,
                              @RequestParam(name= "eus", defaultValue = "false") boolean eus,
                              @RequestParam(name= "cyl", defaultValue = "false") boolean cyl)
    {
        return null;
    }

}
