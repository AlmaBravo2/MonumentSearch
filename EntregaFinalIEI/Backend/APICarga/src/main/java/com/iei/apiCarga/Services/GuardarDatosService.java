package com.iei.apiCarga.Services;

import com.iei.apiCarga.Models.Localidad;
import com.iei.apiCarga.Models.Monumento;
import com.iei.apiCarga.Models.Provincia;
import com.iei.apiCarga.Repositories.LocalidadRepository;
import com.iei.apiCarga.Repositories.MonumentoRepository;
import com.iei.apiCarga.Repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
            System.out.println("Provincia: " + provincia.getNombre() + "CÓDIGO:" + provincia.getCodigo() + " Localidad: " + monumento.getLocalidad().getNombre() + " Monumento: " + monumento.getNombre());
            Localidad localidad = monumento.getLocalidad();

            if(!provinciaRepository.existsByNombre(provincia.getNombre())) {
                provinciaRepository.save(provincia);
            }
            Provincia provinciaGuardada = provinciaRepository.findByNombre(provincia.getNombre());
            localidad.setProvincia(provinciaGuardada);

            if(!localidadRepository.existsByNombre(localidad.getNombre())) {
                localidadRepository.save(localidad);
            }
            Localidad localidadGuardada = localidadRepository.findByNombre(localidad.getNombre());

            monumento.setLocalidad(localidadGuardada);
            if(!monumentoRepository.existsByNombre(monumento.getNombre())) {
                monumentoRepository.save(monumento);
            }

        }

    }
}
