package com.wrapper.Euskadi.Controller;

import com.wrapper.Euskadi.Service.EuskadiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Euskadi", description = "Endpoints para cargar monumentos de Euskadi")
public class EuskadiController {

    @Autowired
    private EuskadiService euskadiService;

    @Operation(
            summary = "Cargar datos de monumentos de Euskadi",
            description = "Este endpoint permite obtener todos los monumentos de Euskadi desde la fuente externa en json."
    )
    @GetMapping("/carga")
    public ResponseEntity<?> carga() {
        return new ResponseEntity<>(euskadiService.getMonumentos(), HttpStatus.OK);
    }
}