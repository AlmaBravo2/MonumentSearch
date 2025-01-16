package com.iei.apiCarga.Controllers;

import com.iei.apiCarga.Services.CargaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Cargar", description = "Endpoints para cargar los monumentos en la base de datos")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    @Operation(
            summary = "Cargar datos en la base de datos",
            description = "Permite cargar datos de monumentos en la base de datos de forma selectiva o completa."
    )
    @PostMapping("/carga/")
    public String cargarDatos(
            @Parameter(description = "Cargar todos los datos disponibles", example = "true")
            @RequestParam(name = "todos", defaultValue = "false") boolean todos,

            @Parameter(description = "Cargar datos de la Comunidad Valenciana", example = "true")
            @RequestParam(name = "cv", defaultValue = "false") boolean cv,

            @Parameter(description = "Cargar datos del País Vasco (Euskadi)", example = "true")
            @RequestParam(name = "eus", defaultValue = "false") boolean eus,

            @Parameter(description = "Cargar datos de Castilla y León", example = "true")
            @RequestParam(name = "cyl", defaultValue = "false") boolean cyl
    ) {
        return cargaService.cargarDatos(todos, cv, eus, cyl);
    }
}