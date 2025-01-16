package com.wrapper.castillaYLeon;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para gestionar la carga de datos de Castilla y León.
 */
@RestController
@Tag(name = "Castilla y León", description = "Endpoints para cargar monumentos de Castilla y Leon")
public class CLEController {

    @Autowired
    private CLEService cleService;

    @Operation(
            summary = "Cargar datos de monumentos de Castilla y Leon",
            description = "Este endpoint permite obtener todos los monumentos de Castilla y Leon desde la fuente externa en xml."
    )
    @GetMapping("/carga")
    public ResponseEntity<?> carga() {
        return new ResponseEntity<>(cleService.cargarDatosCLE(), HttpStatus.OK);
    }
}