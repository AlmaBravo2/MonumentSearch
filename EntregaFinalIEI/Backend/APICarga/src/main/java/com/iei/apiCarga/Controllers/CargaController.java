package com.iei.apiCarga.Controllers;

import com.iei.apiCarga.Services.CargaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para la gestión de la carga de datos de monumentos en la base de datos.
 * Proporciona endpoints para cargar datos selectivamente o completamente,
 * así como para vaciar la base de datos.
 */
@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Cargar", description = "Endpoints para cargar los monumentos de los wrappers en la base de datos")
public class CargaController {

    @Autowired
    private CargaService cargaService;

    /**
     * Endpoint para cargar datos en la base de datos.
     * Este endpoint permite cargar datos de monumentos en la base de datos de forma selectiva o completa.
     *
     * @param todos Indica si se deben cargar todos los datos disponibles.
     * @param cv Indica si se deben cargar datos específicos de la Comunidad Valenciana.
     * @param eus Indica si se deben cargar datos específicos del País Vasco (Euskadi).
     * @param cyl Indica si se deben cargar datos específicos de Castilla y León.
     * @return Mensaje indicando el estado de la operación de carga.
     */
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

    /**
     * Endpoint para eliminar todos los datos de la base de datos.
     * Este endpoint elimina todos los registros de la base de datos relacionados con los monumentos cargados.
     */
    @Operation(
            summary = "Eliminar todos los datos de la base de datos",
            description = "Permite eliminar todos los datos de la base de datos."
    )
    @DeleteMapping("/vaciar")
    public void vaciarDatos() {
        cargaService.vaciarDatos();
    }
}
