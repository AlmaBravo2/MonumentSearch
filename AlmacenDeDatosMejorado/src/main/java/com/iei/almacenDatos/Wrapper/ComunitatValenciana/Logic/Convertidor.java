package com.iei.almacenDatos.Wrapper.ComunitatValenciana.Logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iei.almacenDatos.Wrapper.ComunitatValenciana.Models.Monumento;
import com.iei.almacenDatos.Wrapper.ComunitatValenciana.Models.MonumentoConvertido;
import com.iei.almacenDatos.Wrapper.ComunitatValenciana.Models.MonumentoOriginal;

import com.iei.almacenDatos.Wrapper.ComunitatValenciana.Utils.LevenshteinComparator;
import com.iei.almacenDatos.Wrapper.ComunitatValenciana.Utils.MonumentLocator;

import java.util.ArrayList;
import java.util.List;

import static com.iei.almacenDatos.Wrapper.ComunitatValenciana.Logic.ConvertidorCoordenadas.convertirCoordenadas;

public class Convertidor {

    // Crear el objeto MONUMENTO que se enviará a través de la API
    private static Monumento convertirMonumento(MonumentoConvertido monumento) {
        String nombre = monumento.getDenominacion();
        String tipo = getTipo(monumento);
        String provinciaNombre = monumento.getProvincia();
        if (LevenshteinComparator.calculateLevenshteinDistance(provinciaNombre, "ALICANTE") < 3) {
            provinciaNombre = "ALICANTE";
        }
        else if (LevenshteinComparator.calculateLevenshteinDistance(provinciaNombre, "CASTELLÓN") < 2) {
            provinciaNombre = "CASTELLÓN";
        }
        else if (LevenshteinComparator.calculateLevenshteinDistance(provinciaNombre, "VALENCIA") < 2) {
            provinciaNombre = "VALENCIA";
        }
        String municipio = monumento.getMunicipio();
        String latitud = monumento.getLatitud();
        String longitud = monumento.getLongitud();
        if(latitud.equals("") || latitud.equals("") ||latitud.equals("NaN") || latitud.equals("NaN")) {
            return null;
            // Si no se tienen las coordenadas, no se puede crear el monumento
        }
        String descripcion = monumento.getClasificacion();
        String codPostal = MonumentLocator.getMonumentPostCode(latitud, longitud);
        String direccion = MonumentLocator.getMonumentDirection(latitud, longitud);
        Monumento monumentoConvertido = new Monumento(nombre, tipo, direccion, codPostal, longitud, latitud, descripcion, municipio, provinciaNombre );
        return monumentoConvertido;
    }

    /* MÉTODO QUE OBTIENEN EL TIPO DE UN MONUMENTO A PARTIR DE UNA SERIE DE PALABRAS CLAVE*/
    private static String getTipo(MonumentoConvertido monumento) {
        String nombre = monumento.getDenominacion();
        nombre = nombre.toLowerCase();
        String tipo = "Otros";
        if(nombre.contains("iglesia") || nombre.contains("ermita") || nombre.contains("catedral") || nombre.contains("basílica") || nombre.contains("cartuja"))
        {    tipo = "Iglesia-Ermita";}
        else if (nombre.contains("yacimiento") || nombre.contains("cueva") || nombre.contains("dolmen") || nombre.contains("arqueológico") || nombre.contains("poblado"))
        {    tipo = "Yacimiento arqueológico";}
        else if (nombre.contains("monasterio") || nombre.contains("convento")) {    tipo = "Monasterio-Convento";}
        else if (nombre.contains("castillo") || nombre.contains("torre") || nombre.contains("muralla") || nombre.contains("fortaleza")) {    tipo = "Castillo-Fortaleza-Torre";}
        else if (nombre.contains("palacio") || nombre.contains("casa") || nombre.contains("museo")){    tipo = "Edificio singular";}
        return tipo;

    }

    // MÉTODO QUE A PARTIR DE LOS MONUMENTOS CON LAS COORDENADAS CORREGIDAS, CREA UNA LISTA DE MONUMENTOS DEFINITIVOS.
    public static List<Monumento> getMonumentos(String filePath) {
        List<MonumentoConvertido> monumentosConvertidos = convertirCoordenadas(filePath);
        List<Monumento> monumentos = new ArrayList<>();
        int total = monumentosConvertidos.size();
        int contador = 1;
        for (MonumentoConvertido monumento : monumentosConvertidos) {
                Monumento monumentoConvertido = convertirMonumento(monumento);
                if (monumentoConvertido != null) {
                    monumentos.add(monumentoConvertido);
                    System.out.println(String.format("Monumento %d / %d", contador, total));
                    contador++;
                }
                else{
                    System.err.println("Valores de entrada incorrectos o incompletos");
                    contador++;
                }
        }
        return monumentos;
    }

    // MÉTODO QUE CONVIERTE UNA LISTA DE MONUMENTOS A UN STRING JSON
    public static String monumentosToJSON(List<Monumento> monumentos) {
        ObjectMapper mapper = new ObjectMapper();
        monumentos.removeIf(monumento -> monumento == null);
        try {
            return mapper.writeValueAsString(monumentos);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error converting monumentos to JSON";
        }
    }

    public static String convertidor(String filePath) {
        List<Monumento> monumentos = getMonumentos(filePath);
        return monumentosToJSON(monumentos);
    }

}
