package com.wrapper.Euskadi;


import com.wrapper.Euskadi.Models.*;

import java.util.*;


public class ConversorEuskadi {

    private  int contadorDeCorrectos = 0;
    private static List<MonumentoOrginalEuskadi> monumentos = JsonMonumento.readJson("src/main/java/com/wrapper/Euskadi/DataSource/edificios.json");
    
    public static List<Object> convertirEuskadi() {
        String informe = "<-------------------EUSKADI---------------------->\nSucesos al extraer de la fuente de datos:\n";
         HashMap<String,String> rechazados = new HashMap<>();
         HashMap<String,String> modificados = new HashMap<>();
         int contadorDeInsertados = 0;

        List<MonumentoConvertidoEuskadi> monumentosConvertidos = new ArrayList<>();
        
        for(MonumentoOrginalEuskadi monumento : monumentos){

            String address = monumento.getAddress();
            String postalCode ;
            String tipo = getTipo(monumento);

            if(Double.parseDouble(monumento.getLatwgs84()) < -90 || Double.parseDouble(monumento.getLatwgs84()) > 90 || Double.parseDouble(monumento.getLonwgs84()) < -180 || Double.parseDouble(monumento.getLonwgs84()) > 180){
                rechazados.put(monumento.getDocumentName(),"Coordenadas incorrectas");
                continue;
            }
            if(Objects.equals(monumento.getPostalCode(), "") && monumento.getLatwgs84() != null && monumento.getLonwgs84() != null){
                postalCode = org.example.Utils.MonumentoEuskadiLocator.getMonumentPostCode(monumento.getLonwgs84(), monumento.getLatwgs84());
                modificados.put(monumento.getDocumentName(),"El código postal inexistente añadido");
            }
            else { postalCode = monumento.getPostalCode(); }

            if(monumento.getAddress() == null && monumento.getLatwgs84() != null && monumento.getLonwgs84() != null){
                address = org.example.Utils.MonumentoEuskadiLocator.getMonumentDirection(monumento.getLonwgs84(), monumento.getLatwgs84());
                modificados.put(monumento.getDocumentName(), "Dirección incorrecta.");
            }
            else { address = monumento.getAddress(); }

            Localidad localidad = new Localidad();
            localidad.setNombre(monumento.getMunicipality().toUpperCase());
            Provincia provincia = new Provincia();
            provincia.setCodigo(Integer.parseInt(postalCode.substring(0,2)));
            provincia.setNombre(monumento.getTerritory().toUpperCase());
            localidad.setProvincia(provincia);
            monumentosConvertidos.add(new MonumentoConvertidoEuskadi(
                    monumento.getDocumentName(),
                    tipo,
                    address,
                    postalCode,
                    monumento.getLatwgs84(),
                    monumento.getLonwgs84(),
                    monumento.getDocumentDescription(),
                    localidad

            ));
            contadorDeInsertados++;

        }
        informe += "Monumentos correctos: " + contadorDeInsertados + "\n";
        informe += "Monumentos rechazados: " + rechazados.size() + " -> Desglose: "+ rechazados.toString() + "\n";
        informe += "Monumentos modificados: " + modificados.size() + " -> Desglose: "+ modificados.toString() + "\n";
        return List.of(monumentosConvertidos,informe);
    }

    private static String getTipo(MonumentoOrginalEuskadi monumento) {
        String nombre = monumento.getDocumentName().toLowerCase();
        String tipo = "Otros";

        if(nombre.contains("iglesia") || nombre.contains("ermita") || nombre.contains("catedral") || nombre.contains("basílica")){
            tipo = "Iglesia-Ermita";
        } else if (nombre.contains("yacimiento") || nombre.contains("cueva") || nombre.contains("dolmen") ){
            tipo = "Yacimiento arqueológico";
        } else if (nombre.contains("monasterio") || nombre.contains("convento")) {
            tipo = "Monasterio-Convento";
        } else if (nombre.contains("castillo") || nombre.contains("torre") || nombre.contains("muralla") || nombre.contains("fortaleza")) {
            tipo = "Castillo-Fortaleza-Torre";
        } else if (nombre.contains("palacio") || nombre.contains("casa") || nombre.contains("museo")){
            tipo = "Edificio singular";
        }
        return tipo;
    }
}