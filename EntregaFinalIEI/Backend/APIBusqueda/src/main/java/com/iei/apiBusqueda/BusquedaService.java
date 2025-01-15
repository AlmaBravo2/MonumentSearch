package com.iei.apiBusqueda;

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
        // Obtén todos los monumentos del repositorio
        List<Monumento> monumentos = monumentRepository.findAll();

        // Filtra los monumentos según los criterios proporcionados
        List<Monumento> filtrados = monumentos.stream()
                .filter(monumento -> localidad == null || localidad.equals(monumento.getLocalidad().getNombre()))
                .filter(monumento -> codPostal == null || codPostal.equals(monumento.getCodigoPostal()))
                .filter(monumento -> provincia == null || provincia.equals(monumento.getLocalidad().getProvincia().getNombre()))
                .filter(monumento -> tipo == null || tipo.equals(monumento.getTipo()))
                .collect(Collectors.toList());

        // Convierte los monumentos filtrados a DTOs
        List<MonumentoDTO> res = new ArrayList<>();
        for (Monumento monumento : filtrados) {
            MonumentoDTO monumentoDTO = new MonumentoDTO(
                    monumento,
                    monumento.getLocalidad(),
                    monumento.getLocalidad().getProvincia()
            );
            res.add(monumentoDTO);
        }

        return res;
    }
}
