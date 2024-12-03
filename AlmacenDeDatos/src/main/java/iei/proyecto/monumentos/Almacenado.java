package iei.proyecto.monumentos;

import iei.proyecto.monumentos.Models.*;

import iei.proyecto.monumentos.BDconexion.*;

import iei.proyecto.monumentos.ModelsJson.*;

import java.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class Almacenado {
    private BDconexion conexion = new BDconexion();
    DataSource dataSource = conexion.dataSource();
    JdbcTemplate jdbcTemplate = conexion.jdbcTemplate(dataSource);

    private LocalidadBD localidadBD = new LocalidadBD(jdbcTemplate);
    private ProvinciaBD provinciaBD = new ProvinciaBD(jdbcTemplate);
    private MonumentoBD monumentoBD = new MonumentoBD(jdbcTemplate);

    public Almacenado(){}

    /**
     * Convierte una cadena JSON en una lista de objetos MonumentoJson.
     *
     * @param jsonString Cadena JSON que representa la lista de monumentos.
     * @return Lista de objetos MonumentoJson.
     * @throws IOException Si ocurre un error al deserializar el JSON.
     */
    public static List<MonumentoJson> deserializarMonumentos(String jsonString) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, new TypeReference<List<MonumentoJson>>() {});
    }

    private void datosAlmacenar(List<MonumentoJson> monumento){
        for(MonumentoJson monument: monumento){
            Localidad localidad = transformarLocalidad(monument.getLocalidad(), monument.getProvincia());
            Monumento monumentoAlmacenar = new Monumento(0, monument.getNombre(), monument.getDireccion(), monument.getCodigoPostal(), monument.getLongitud(), monument.getLatitud(), monument.getDescripcion(), Tipo.valueOf(monument.getTipo()), localidad);
        }
    }

    private Localidad transformarLocalidad(LocalidadJson localidadjson, ProvinciaJson provinviajson){
        Provincia provincia = transformarProvincia(provinviajson);
        Localidad localidad = new Localidad(0, localidadjson.getNombre(), provincia);
        return localidad;
    }

    private Provincia transformarProvincia(ProvinciaJson provinciajson){
        Provincia provincia = new Provincia(Integer.parseInt(provinciajson.getCodigo()), provinciajson.getNombre());
        return  provincia;
    }

    private void almacenar(){

    }

}