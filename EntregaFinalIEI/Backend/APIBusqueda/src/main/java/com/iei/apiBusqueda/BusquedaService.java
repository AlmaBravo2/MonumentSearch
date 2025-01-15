package com.iei.apiBusqueda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iei.apiBusqueda.Models.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusquedaService {

    @Autowired
    private MonumentRepository monumentRepository;

    public List<MonumentoDTO> monumentSearch(String localidad, String codPostal, String provincia, String tipo){

        List<MonumentoDTO> res = new ArrayList<>();
        List<Monumento> monumentos = monumentRepository.findAll();

        if(localidad != null){
           for(Monumento monumento : monumentos){
               if(!monumento.getLocalidad().getNombre() .equals(localidad)) monumentos.remove(monumento);
           }
        }

        if(codPostal != null){
            for(Monumento monumento : monumentos){
                if(!monumento.getCodigoPostal().equals(codPostal)) monumentos.remove(monumento);
            }
        }

        if(provincia != null){
            for(Monumento monumento : monumentos){
                if(!monumento.getLocalidad().getProvincia().getNombre().equals(provincia)) monumentos.remove(monumento);
            }
        }
        if(tipo != null){
            for(Monumento monumento : monumentos){
                if(!monumento.getTipo().equals(tipo)) monumentos.remove(monumento);
            }
        }
        for(Monumento monumento : monumentos){
            MonumentoDTO monumentoDTO = new MonumentoDTO(monumento,monumento.getLocalidad(),monumento.getLocalidad().getProvincia());
            res.add(monumentoDTO);
        }
        return res;
    }
}
