package com.iei.almacenDatos.Wrapper.Euskadi.Logic;

import com.iei.almacenDatos.Wrapper.Euskadi.Models.MonumentoConvertidoEuskadi;
import com.iei.almacenDatos.Wrapper.Euskadi.Models.MonumentoOrginalEuskadi;
import com.iei.almacenDatos.Wrapper.Euskadi.Logic.JsonMonumento;
import com.iei.almacenDatos.Wrapper.Euskadi.Utils.MonumentoEuskadiLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ConversorEuskadi {
    

    public static List<MonumentoConvertidoEuskadi> convertirEuskadi(String filePath) {

        List<MonumentoOrginalEuskadi> monumentos = JsonMonumento.readJson(filePath);

        List<MonumentoConvertidoEuskadi> monumentosConvertidos = new ArrayList<>();
        
        for(MonumentoOrginalEuskadi monumento : monumentos){

            String address = monumento.getAddress();
            String postalCode ;
            String tipo = getTipo(monumento);


            if(Objects.equals(monumento.getPostalCode(), "") && monumento.getLatwgs84() != null && monumento.getLonwgs84() != null){
                postalCode = MonumentoEuskadiLocator.getMonumentPostCode(monumento.getLonwgs84(), monumento.getLatwgs84());
            }
            else { postalCode = monumento.getPostalCode(); }

            if(monumento.getAddress() == null && monumento.getLatwgs84() != null && monumento.getLonwgs84() != null){
                address = MonumentoEuskadiLocator.getMonumentDirection(monumento.getLonwgs84(), monumento.getLatwgs84());
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

    public static String convertirEuskadiToJson(String filePath) {
        List<MonumentoConvertidoEuskadi> monumentosConvertidos = convertirEuskadi(filePath);
        return JsonMonumento.writeJson(monumentosConvertidos);
    }
}