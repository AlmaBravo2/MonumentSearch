package com.iei.apiCarga.Controllers;

import com.iei.apiCarga.Services.CargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @PostMapping("/carga/")
    public String cargarDatos(@RequestParam(name= "todos", defaultValue = "false") boolean todos,
                              @RequestParam(name= "cv", defaultValue = "false") boolean cv,
                              @RequestParam(name= "eus", defaultValue = "false") boolean eus,
                              @RequestParam(name= "cyl", defaultValue = "false") boolean cyl)
    {
        return cargaService.cargarDatos(todos, cv, eus, cyl);
    }

}
