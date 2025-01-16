package com.wrapper.comunidadValenciana;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Comunidad Valenciana", description = "Endpoints para cargar datos de la Comunidad Valenciana")
public class ValenciaController {

    @Autowired
    private ValenciaService valenciaService;

    @Operation(
            summary = "Cargar datos de la Comunidad Valenciana",
            description = "Este endpoint permite cargarlos datos de la Comunidad Valenciana de la fuente externa en csv."
    )
    @GetMapping("/carga")
    public ResponseEntity<?> carga() {
        return new ResponseEntity<>(valenciaService.cargarDatos(), HttpStatus.OK);
    }
}
