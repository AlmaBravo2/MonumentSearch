package com.iei.apiBusqueda;
/**
 * Clase que se encarga de realizar la búsqueda de monumentos en la base de datos.
 * @author M12.01
 * @version 1.0
 * */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iei.apiBusqueda.Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusquedaService {

    @Autowired
    private MonumentRepository monumentRepository;

    public List<MonumentoDTO> monumentSearch(String localidad, String codPostal, String provincia, String tipo) {

        // Obtenemos todos los métodos almacenados en la base de datos para posteriormente filtrar.
        List<Monumento> monumentos = monumentRepository.findAll();

        /*
        Posteriormente, observamos cuáles de los parámetros de búsqueda están activados como true y filtramos empleando la programación funcional
         para obtener los monumentos que cumplan con los criterios de búsqueda.
         */
        List<Monumento> filtrados = monumentos.stream()
                .filter(monumento -> localidad.equals("null") || localidad.equals(monumento.getLocalidad().getNombre()))
                .filter(monumento -> codPostal.equals("null") || codPostal.equals(monumento.getCodigoPostal()))
                .filter(monumento -> provincia.equals("null") || provincia.equals(monumento.getLocalidad().getProvincia().getNombre()))
                .filter(monumento -> tipo.equals("null") || tipo.equals(monumento.getTipo()))
                .collect(Collectors.toList());

        // Una vez ya tenemos los monumentos filtrados, los convertimos a DTO para enviarlos a través de la API de una manera que sigan el esquema global definido.
        List<MonumentoDTO> res = getMonumentoDTOS(filtrados);
        return res;
    }

    // Método auxiliar para convertir los monumentos a DTO.
    private static List<MonumentoDTO> getMonumentoDTOS(List<Monumento> filtrados) {
        List<MonumentoDTO> res = new ArrayList<>();
        for (Monumento monumento : filtrados) {
            MonumentoDTO monumentoDTO = new MonumentoDTO(
                    monumento,
                    monumento.getLocalidad()
            );
            res.add(monumentoDTO);
        }
        return res;
    }
}
