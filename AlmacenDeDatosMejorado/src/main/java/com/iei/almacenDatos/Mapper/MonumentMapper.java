package com.iei.almacenDatos.Mapper;

import com.iei.almacenDatos.DTOs.MonumentoDTO;
import com.iei.almacenDatos.Models.Localidad;
import com.iei.almacenDatos.Models.Monumento;
import com.iei.almacenDatos.Models.Provincia;

import java.util.HashMap;

public class MonumentMapper {

  public static HashMap<String,Object>  monumentMapper(MonumentoDTO monumentoDTO){
    HashMap<String,Object> datos_monumento = new HashMap<>();

    Monumento monumento1 = new Monumento();
    monumento1.setNombre(monumentoDTO.getNombre());
    monumento1.setDireccion(monumentoDTO.getDireccion());
    monumento1.setCodigoPostal(monumentoDTO.getCodigo_postal());
    monumento1.setLongitud(monumentoDTO.getLongitud());
    monumento1.setLatitud(monumentoDTO.getLatitud());
    monumento1.setDescripcion(monumentoDTO.getDescripcion());
    monumento1.setTipo(monumentoDTO.getTipo());

    Localidad localidad = new Localidad();
    localidad.setNombre(monumentoDTO.getLocalidad());
    monumento1.setLocalidad(localidad);

    String provinciaCodigo = monumentoDTO.getCodigo_postal().substring(0,2);
    String provinciaNombre = monumentoDTO.getProvincia();
    Provincia provincia = new Provincia();
    provincia.setCodigo(Integer.parseInt(provinciaCodigo));
    provincia.setNombre(provinciaNombre);

    datos_monumento.put("monumento",monumento1);
    datos_monumento.put("provincia",provincia);
    datos_monumento.put("localidad",localidad);
    return datos_monumento;
  }
}
