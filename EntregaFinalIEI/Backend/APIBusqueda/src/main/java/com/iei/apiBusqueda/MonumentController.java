package com.iei.apiBusqueda;

import com.iei.apiBusqueda.MonumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monumentos")
@CrossOrigin(origins = "http://localhost:3000")
public class MonumentController {
    @Autowired
    private BusquedaService busquedaService;
    @GetMapping("/")
    public List<MonumentoDTO> getMonumentos(@RequestParam (name="localidad", defaultValue = "null") String localidad, @RequestParam (name="cod postal", defaultValue = "null") String codPostal,
                                            @RequestParam (name="provincia", defaultValue = "null") String provincia, @RequestParam (name="tipo", defaultValue = "null") String tipo)
    {
        return busquedaService.monumentSearch(localidad,codPostal,provincia,tipo);
    }


}
