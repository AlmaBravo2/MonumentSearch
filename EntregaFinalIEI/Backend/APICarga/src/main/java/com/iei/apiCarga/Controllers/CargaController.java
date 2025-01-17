package com.iei.apiCarga.Controllers;

import com.iei.apiCarga.Models.ParamsDTO;
import com.iei.apiCarga.Services.CargaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller para la gestión de la carga de datos de monumentos en la base de datos.
 * Proporciona endpoints para cargar datos selectivamente o completamente,
 * así como para vaciar la base de datos.
 */
@RestController
@CrossOrigin
@Tag(name = "Cargar", description = "Endpoints para cargar los monumentos de los wrappers en la base de datos")
public class CargaController {

    @Autowired
    private CargaService cargaService;


    /**
     * Endpoint para cargar datos en la base de datos.
     * Este endpoint permite cargar datos de monumentos en la base de datos de forma selectiva o completa.
     *
        * @param params Parámetros de carga
     */
    @CrossOrigin
    @Operation(
            summary = "Cargar datos en la base de datos",
            description = "Permite cargar datos de monumentos en la base de datos de forma selectiva o completa."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Datos cargados correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error al cargar los datos")
    })
    @PostMapping("/carga/")
    public ResponseEntity<?> cargarDatos(@RequestBody @Parameter(description = "Parámetros de carga") ParamsDTO params) {
        try {
            // Cargamos los datos, retornamos el informe con el header Allow
            HttpHeaders headers = new HttpHeaders();
            headers.add("Access-Control-Allow-Origin", "GET, POST, PUT, DELETE, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "*");
            headers.add("Access-Control-Allow-Credentials", "true");
            headers.add("Access-Control-Allow-Methods", "*");
            headers.add("Access-Control-Expose-Headers", "*");
            headers.add("Access-Control-Max-Age", "3600");

            return new ResponseEntity<>(cargaService.cargarDatos(params.isTodos(), params.isCv(), params.isEus(), params.isCyl()), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Endpoint para eliminar todos los datos de la base de datos.
     * Este endpoint elimina todos los registros de la base de datos relacionados con los monumentos cargados.
     */
    @Operation(
            summary = "Eliminar todos los datos de la base de datos",
            description = "Permite eliminar todos los datos de la base de datos."
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Datos eliminados correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Error al eliminar los datos")
    })
    @DeleteMapping("/vaciar")
    public ResponseEntity<?> vaciarDatos() {
        try{
            cargaService.vaciarDatos();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
