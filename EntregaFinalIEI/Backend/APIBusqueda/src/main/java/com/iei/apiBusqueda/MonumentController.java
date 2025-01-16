package com.iei.apiBusqueda;

import com.iei.apiBusqueda.MonumentoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monumentos")
@CrossOrigin(origins = "*")
@Tag(name = "Monumentos", description = "Endpoints para buscar monumentos")
public class MonumentController {
    @Autowired
    private BusquedaService busquedaService;

    @Operation(
            summary = "Buscar monumentos",
            description = "Obtiene una lista de monumentos filtrados por localidad, código postal, provincia y tipo."
    )
    @GetMapping("/")
    public List<MonumentoDTO> getMonumentos(
            @Parameter(description = "Nombre de la localidad", example = "VALENCIA")
            @RequestParam(name = "localidad", defaultValue = "null") String localidad,

            @Parameter(description = "Código postal de la localidad", example = "46760")
            @RequestParam(name = "codigoPostal", defaultValue = "null") String codPostal,

            @Parameter(description = "Nombre de la provincia", example = "VALENCIA")
            @RequestParam(name = "provincia", defaultValue = "null") String provincia,

            @Parameter(description = "Tipo de monumento", example = "Histórico")
            @RequestParam(name = "tipo", defaultValue = "null") String tipo
    ) {
        return busquedaService.monumentSearch(localidad, codPostal, provincia, tipo);
    }


}
