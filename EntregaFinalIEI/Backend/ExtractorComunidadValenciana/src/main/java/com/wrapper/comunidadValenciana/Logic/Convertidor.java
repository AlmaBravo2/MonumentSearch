package com.wrapper.comunidadValenciana.Logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wrapper.comunidadValenciana.Models.Monumento;
import com.wrapper.comunidadValenciana.Models.MonumentoConvertido;
import com.wrapper.comunidadValenciana.Models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.wrapper.comunidadValenciana.Models.*;
import com.wrapper.comunidadValenciana.Utils.LevenshteinComparator;
import com.wrapper.comunidadValenciana.Utils.MonumentLocator;
import org.checkerframework.checker.units.qual.C;

import static com.wrapper.comunidadValenciana.Logic.ConvertidorCoordenadas.convertirCoordenadas;

public class Convertidor {

    private  String informe = "<-------------------VALENCIA---------------------->\nSucesos al extraer de la fuente de datos:\n";
    private  HashMap<String,String> rechazados = new HashMap<>();
    private  HashMap<String,String> modificados = new HashMap<>();
    private  int contadorDeCorrectos = 0;

    // Crear el objeto MONUMENTO que se enviará a través de la API
    private Monumento convertirMonumento(MonumentoConvertido monumento) {

        String nombre = monumento.getDenominacion();
        String tipo = getTipo(monumento);
        String provinciaNombre = monumento.getProvincia();

        if (LevenshteinComparator.calculateLevenshteinDistance(provinciaNombre, "ALICANTE") < 3  && !provinciaNombre.equals("ALICANTE")) {
            modificados.put(nombre,"Error tipográfico en el nombre de la provincia");
            provinciaNombre = "ALICANTE";
        }
        else if (LevenshteinComparator.calculateLevenshteinDistance(provinciaNombre, "CASTELLÓN") < 2  && !provinciaNombre.equals("CASTELLÓN")) {
            modificados.put(nombre,"Error tipográfico en el nombre de la provincia");
            provinciaNombre = "CASTELLÓN";
        }
        else if (LevenshteinComparator.calculateLevenshteinDistance(provinciaNombre, "VALENCIA") < 2  && !provinciaNombre.equals("VALENCIA")) {
            modificados.put(nombre,"Error tipográfico en el nombre de la provincia");
            provinciaNombre = "VALENCIA";
        }

        String municipio = monumento.getMunicipio();
        String latitud = monumento.getLatitud();
        String longitud = monumento.getLongitud();
        if(latitud.equals("") || latitud.equals("") ||latitud.equals("NaN") || latitud.equals("NaN")) {
            rechazados.put(nombre,"No tiene coordenadas");
            return null;
            // Si no se tienen las coordenadas, no se puede crear el monumento
        }
        String descripcion = monumento.getClasificacion();
        String codPostal = MonumentLocator.getMonumentPostCode(latitud, longitud);
        if(codPostal.startsWith("3") && !provinciaNombre.equals("ALICANTE")) {modificados.put(nombre,"Nombre de provincia no concordante con el códifo postal");provinciaNombre = "ALICANTE";}
        else if(codPostal.startsWith("12") && !provinciaNombre.equals("CASTELLÓN")) {modificados.put(nombre,"Nombre de provincia no concordante con el códifo postal");provinciaNombre = "CASTELLÓN";}
        else if(codPostal.startsWith("46") && !provinciaNombre.equals("VALENCIA")) {modificados.put(nombre,"Nombre de provincia no concordante con el códifo postal");provinciaNombre = "VALENCIA";}

        Provincia provincia = new Provincia(Integer.parseInt(codPostal.substring(0, 2)), provinciaNombre);
        Localidad localidad = new Localidad();
        localidad.setNombre(municipio);
        localidad.setProvincia(provincia);
        String direccion = MonumentLocator.getMonumentDirection(latitud, longitud);
        Monumento monumentoConvertido = new Monumento(nombre, tipo, direccion, codPostal, longitud, latitud, descripcion, localidad);
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
    public MonumentosDTO getMonumentos(String filePath) {
        List<MonumentoConvertido> monumentosConvertidos = convertirCoordenadas(filePath);
        List<Monumento> monumentos = new ArrayList<>();
        int total = monumentosConvertidos.size();
        int contador = 1;
        for (MonumentoConvertido monumento : monumentosConvertidos) {
                Monumento monumentoConvertido = convertirMonumento(monumento);
                if (monumentoConvertido != null) {
                    contadorDeCorrectos++;
                    monumentos.add(monumentoConvertido);
                    System.out.println(String.format("Monumento %d / %d", contador, total));
                    contador++;
                }
                else{
                    System.err.println("Valores de entrada incorrectos o incompletos");
                    contador++;
                }
        }

        informe += "Monumentos correctos: " + contadorDeCorrectos + "\n";
        informe += "Monumentos rechazados: " + rechazados.size() + " -> Desglose: "+ rechazados.toString() + "\n";
        informe += "Monumentos modificados: " + modificados.size() + " -> Desglose: "+ modificados.toString() + "\n";
        return new MonumentosDTO(monumentos.stream().distinct().collect(Collectors.toList()), informe);
    }

    // MÉTODO QUE CONVIERTE UNA LISTA DE MONUMENTOS A UN STRING JSON
    public static String monumentosToJSON(MonumentosDTO mdto) {
        ObjectMapper mapper = new ObjectMapper();
        List<Monumento> monumentos = mdto.getMonumentos();
        monumentos.removeIf(monumento -> monumento == null);
        try {
            return mapper.writeValueAsString(monumentos);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error converting monumentos to JSON";
        }
    }

    public static String convertidor(String filePath) {
        Convertidor convertidor = new Convertidor();
        MonumentosDTO monumentos = convertidor.getMonumentos(filePath);
        return monumentosToJSON(monumentos);
    }

}
