package com.iei.apiBusqueda;

import com.iei.apiBusqueda.MonumentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monumentos")
public class MonumentController {
    @Autowired
    private BusquedaService busquedaService;
    @GetMapping("/")
    public List<MonumentoDTO> getMonumentos(@RequestParam (name="localidad") String localidad, @RequestParam (name="cod postal") String codPostal,
                                            @RequestParam (name="provincia") String provincia, @RequestParam (name="tipo") String tipo)
    {
        return busquedaService.monumentSearch(localidad,codPostal,provincia,tipo);
    }


}
