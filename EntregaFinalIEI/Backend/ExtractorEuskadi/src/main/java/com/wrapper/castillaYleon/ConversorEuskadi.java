package com.wrapper.castillaYleon;



import com.wrapper.castillaYleon.Models.MonumentoConvertidoEuskadi;
import com.wrapper.castillaYleon.Models.MonumentoOrginalEuskadi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ConversorEuskadi {
    
    private static List<MonumentoOrginalEuskadi> monumentos = JsonMonumento.readJson("src/main/java/org/example/Data/edificiosEntrega.json");
    
    public static List<MonumentoConvertidoEuskadi> convertirEuskadi() {

        List<MonumentoConvertidoEuskadi> monumentosConvertidos = new ArrayList<>();
        
        for(MonumentoOrginalEuskadi monumento : monumentos){

            String address = monumento.getAddress();
            String postalCode ;
            String tipo = getTipo(monumento);


            if(Objects.equals(monumento.getPostalCode(), "") && monumento.getLatwgs84() != null && monumento.getLonwgs84() != null){
                postalCode = org.example.Utils.MonumentoEuskadiLocator.getMonumentPostCode(monumento.getLonwgs84(), monumento.getLatwgs84());
            }
            else { postalCode = monumento.getPostalCode(); }

            if(monumento.getAddress() == null && monumento.getLatwgs84() != null && monumento.getLonwgs84() != null){
                address = org.example.Utils.MonumentoEuskadiLocator.getMonumentDirection(monumento.getLonwgs84(), monumento.getLatwgs84());
            }
            else { address = monumento.getAddress(); }

            monumentosConvertidos.add(new MonumentoConvertidoEuskadi(
                    monumento.getDocumentName(),
                    tipo,
                    address,
                    postalCode,
                    monumento.getLatwgs84(),
                    monumento.getLonwgs84(),
                    monumento.getDocumentDescription(),
                    monumento.getTerritory(),
                    monumento.getMunicipality()
            ));


        }

        return monumentosConvertidos;
    }

    private static String getTipo(MonumentoOrginalEuskadi monumento) {
        String nombre = monumento.getDocumentName().toLowerCase();
        String tipo = "Otro";

        if(nombre.contains("iglesia") || nombre.contains("ermita") || nombre.contains("catedral") || nombre.contains("basílica")){
            tipo = "Iglesia-Ermita";
        } else if (nombre.contains("yacimiento") || nombre.contains("cueva") || nombre.contains("dolmen") ){
            tipo = "Yacimiento arqueológico";
        } else if (nombre.contains("monasterio") || nombre.contains("convento")) {
            tipo = "Monasterio-Convento";
        } else if (nombre.contains("castillo") || nombre.contains("torre") || nombre.contains("muralla") || nombre.contains("fortaleza")) {
            tipo = "Castillo-Fortalez-Torre";
        } else if (nombre.contains("palacio") || nombre.contains("casa") || nombre.contains("museo")){
            tipo = "Edificio singular";
        }
        return tipo;
    }

    public static void main(String[] args) {
        List<MonumentoConvertidoEuskadi> monumentosConvertidos = convertirEuskadi();
        JsonMonumento.writeJson(monumentosConvertidos, "src/main/java/org/example/Data/monumentosEuskadiConvertidos.json");
    }
}