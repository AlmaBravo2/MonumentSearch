package com.iei.apiCarga.Services;

import com.iei.apiCarga.Models.Localidad;
import com.iei.apiCarga.Models.Monumento;
import com.iei.apiCarga.Models.Provincia;
import com.iei.apiCarga.Repositories.LocalidadRepository;
import com.iei.apiCarga.Repositories.MonumentoRepository;
import com.iei.apiCarga.Repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GuardarDatosService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private MonumentoRepository monumentoRepository;

    public void guardarDatos(List<Monumento> monumentos) {
        for(Monumento monumento : monumentos) {
            Provincia provincia = monumento.getLocalidad().getProvincia();
            Localidad localidad = monumento.getLocalidad();

            if(!monumentoRepository.existsByNombre(monumento.getNombre())) {
                if(!provinciaRepository.existsByNombre(provincia.getNombre())) {
                    provinciaRepository.save(provincia);
                }
                if(!localidadRepository.existsByNombre(localidad.getNombre())) {
                    localidadRepository.save(localidad);
                }
                monumentoRepository.save(monumento);
            }

        }

    }
}
