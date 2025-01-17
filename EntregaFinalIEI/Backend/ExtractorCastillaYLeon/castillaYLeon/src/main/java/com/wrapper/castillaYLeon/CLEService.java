package com.wrapper.castillaYLeon;

import com.wrapper.castillaYLeon.Models.*;
import com.wrapper.castillaYLeon.Wrapper.ConvertidorCLE;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CLEService {
    public MonumentosDTO cargarDatosCLE(){
        ConvertidorCLE convertidorCLE = new ConvertidorCLE();
        String filePath =  "monumentosFinal.xml";
        return getMonumentosCLE(filePath);
    }

    public MonumentosDTO getMonumentosCLE(String filepath){
        ConvertidorCLE convertidorCLE = new ConvertidorCLE();
        List<MonumentoCLE> monumentosObtenidos = convertidorCLE.readXML(filepath);

        Map<String, String> modificados = convertidorCLE.getModificados();
        Map<String, String> rechazados = convertidorCLE.getRechazados();

        int numModificados = modificados.size();
        int numRechazados = rechazados.size();

        convertidorCLE.informe += "Monumentos correctos: " + convertidorCLE.contadorDeCorrectos + "\n";
        convertidorCLE.informe += "Monumentos rechazados: " + numRechazados + " -> Desglose: " + rechazados + "\n";
        convertidorCLE.informe += "Monumentos modificados: " + numModificados + " -> Desglose: " + modificados + "\n";

        //Convertir MonumentoCLE a MonumentoDTO
        List<MonumentoCLE> monumentos = monumentosObtenidos.stream().distinct().collect(Collectors.toList());

        List<MonumentoDTO> monumentosDTO = new ArrayList<>();
        for (MonumentoCLE monumento : monumentos) {
            Localidad localidad = new Localidad();
            Provincia provincia = new Provincia();
            provincia.setNombre(monumento.getProvincia().toUpperCase());
            provincia.setCodigo(Integer.parseInt(monumento.getCodigo_postal().substring(0, 2)));
            localidad.setNombre(monumento.getLocalidad().toUpperCase());
            localidad.setProvincia(provincia);
            MonumentoDTO monumentoDTO = new MonumentoDTO();
            monumentoDTO.setNombre(monumento.getNombre());
            monumentoDTO.setTipo(monumento.getTipo());
            monumentoDTO.setDireccion(monumento.getDireccion());
            monumentoDTO.setCodigoPostal(monumento.getCodigo_postal());
            monumentoDTO.setLongitud(monumento.getLongitud());
            monumentoDTO.setLatitud(monumento.getLatitud());
            String descripcion = monumento.getDescripcion();
            descripcion = descripcion.substring(0, Math.min(descripcion.length(), 1000));
            monumentoDTO.setDescripcion(descripcion);
            monumentoDTO.setLocalidad(localidad);
            monumentosDTO.add(monumentoDTO);
        }

        return new MonumentosDTO(monumentosDTO, convertidorCLE.informe);
    }
}
