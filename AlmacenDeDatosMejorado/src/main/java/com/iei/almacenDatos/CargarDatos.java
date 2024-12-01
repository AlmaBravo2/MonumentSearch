package com.iei.almacenDatos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iei.almacenDatos.DTOs.MonumentoDTO;
import com.iei.almacenDatos.Mapper.MonumentMapper;
import com.iei.almacenDatos.Models.Localidad;
import com.iei.almacenDatos.Models.Monumento;
import com.iei.almacenDatos.Models.Provincia;
import com.iei.almacenDatos.Repositories.LocalidadRepository;
import com.iei.almacenDatos.Repositories.MonumentoRepository;
import com.iei.almacenDatos.Repositories.ProvinciaRepository;
import com.iei.almacenDatos.Wrapper.ComunitatValenciana.ComunitatValenciana;
import com.iei.almacenDatos.Wrapper.Euskadi.Euskadi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CargarDatos {

    private MonumentoRepository monumentoRepository;
    private LocalidadRepository localidadRepository;
    private ProvinciaRepository provinciaRepository;
    private List<String> monumentos = new ArrayList<>();
    private final String comunitatValenciana = ComunitatValenciana.main("src/main/java/com/iei/almacenDatos/DataToTest/bienes_inmuebles_interes_cultural.csv");
    private final String euskadi = Euskadi.main("src/main/java/com/iei/almacenDatos/DataToTest/edificios.json");
    //private final String castillaYLeon =

    public CargarDatos(MonumentoRepository monumentoRepository, LocalidadRepository localidadRepository, ProvinciaRepository provinciaRepository) {
        this.monumentoRepository = monumentoRepository;
        this.localidadRepository = localidadRepository;
        this.provinciaRepository = provinciaRepository;
    }

   public void cargarDatos(){
       ObjectMapper objectMapper = new ObjectMapper();
       monumentos.add(comunitatValenciana);
       monumentos.add(euskadi);
       //monumentos.add(castillaYLeon);

       for(String res : monumentos) {
           try {
               List<MonumentoDTO> monumentos = objectMapper.readValue(res, new TypeReference<List<MonumentoDTO>>() {
               });
               List<HashMap<String, Object>> monumentosMap = new ArrayList<>();
               for (MonumentoDTO monumento : monumentos) {
                   monumentosMap.add(MonumentMapper.monumentMapper(monumento));
               }

               for (HashMap<String, Object> monumentoHashMap : monumentosMap) {
                   Monumento monumento = (Monumento) monumentoHashMap.get("monumento");
                   Localidad localidad = (Localidad) monumentoHashMap.get("localidad");
                   Provincia provincia = (Provincia) monumentoHashMap.get("provincia");

                   System.out.println("Provincia: " + provincia.getNombre());
                   System.out.println("Localidad: " + localidad.getNombre());
                   System.out.println("Monumento: " + monumento.getNombre());


                   try { // Si la provincia ya existe, no se guarda
                       provinciaRepository.save(provincia);
                   } catch (Exception e) {
                       System.out.println("Provincia ya existente");
                   }
                   Provincia provincia1 = provinciaRepository.findByNombre(provincia.getNombre()).get();
                   localidad.setProvincia(provincia1);
                   try { // Si la localidad ya existe, no se guarda
                       localidadRepository.save(localidad);
                   } catch (Exception e) {
                       System.out.println("Localidad ya existente");
                   }
                   Localidad localidad1 = localidadRepository.findByNombre(localidad.getNombre()).get();
                   monumento.setLocalidad(localidad1);

                   System.out.println(monumento.getTipo());
                   try {
                       monumentoRepository.save(monumento);
                   } catch (Exception e) {
                       System.out.println("Monumento ya existente");
                   }

               }

               System.out.println("Datos cargados correctamente");


           } catch (JsonProcessingException e) {
               throw new RuntimeException(e);
           }
       }
   }
}
