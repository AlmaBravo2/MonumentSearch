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

    public CargarDatos(MonumentoRepository monumentoRepository, LocalidadRepository localidadRepository, ProvinciaRepository provinciaRepository) {
        this.monumentoRepository = monumentoRepository;
        this.localidadRepository = localidadRepository;
        this.provinciaRepository = provinciaRepository;
    }

   public void cargarDatos(){
       ObjectMapper objectMapper = new ObjectMapper();
       String res = ComunitatValenciana.main("src/main/java/com/iei/almacenDatos/DataToTest/bienes_inmuebles_interes_cultural.csv");
       try {
           List<MonumentoDTO> monumentos = objectMapper.readValue(res, new TypeReference<List<MonumentoDTO>>() {});
           List<HashMap<String, Object>> monumentosMap = new ArrayList<>();
           for (MonumentoDTO monumento : monumentos) {
               monumentosMap.add(MonumentMapper.monumentMapper(monumento));
           }

           for(HashMap<String, Object> monumentoHashMap : monumentosMap){
               Monumento monumento = (Monumento) monumentoHashMap.get("monumento");
               Localidad localidad = (Localidad) monumentoHashMap.get("localidad");
               Provincia provincia = (Provincia) monumentoHashMap.get("provincia");

               System.out.println("Provincia: " + provincia.getNombre());
                System.out.println("Localidad: " + localidad.getNombre());
                System.out.println("Monumento: " + monumento.getNombre());

                Thread.sleep(1000);
               provinciaRepository.save(provincia);
               Thread.sleep(1000);
               localidadRepository.save(localidad);
               Thread.sleep(1000);
               monumentoRepository.save(monumento);



           }

           System.out.println("Datos cargados correctamente");


       } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
   }
}
