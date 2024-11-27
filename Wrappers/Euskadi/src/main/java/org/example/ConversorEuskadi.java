package org.example;

import org.example.Models.MonumentoConvertidoEuskadi;
import org.example.Models.MonumentoOrginalEuskadi;
import org.example.Utils.MonumentoEuskadiLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;


public class ConversorEuskadi {
    
    private static List<MonumentoOrginalEuskadi> monumentos = JsonMonumento.readJson("src/main/java/org/example/Data/edificios.json");
    
    public static List<MonumentoConvertidoEuskadi> convertirEuskadi() {

        List<MonumentoConvertidoEuskadi> monumentosConvertidos = new ArrayList<>();
        
        for(MonumentoOrginalEuskadi monumento : monumentos){






            String postalCode = monumento.getPostalCode();
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

            if(monumento.getPostalCode().isEmpty()){
                postalCode = MonumentoEuskadiLocator.getMonumentPostCode(monumento.getLonwgs84(), monumento.getLatwgs84());
            }

            monumentosConvertidos.add(new MonumentoConvertidoEuskadi(
                    monumento.getDocumentName(),
                    tipo,
                    MonumentoEuskadiLocator.getMonumentDirection(monumento.getLonwgs84(), monumento.getLatwgs84()),
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
   public static void main(String[] args) {
        System.out.println("Hello world!");
        List<MonumentoConvertidoEuskadi> monumentosConvertidos = convertirEuskadi();
        JsonMonumento.writeJson(monumentosConvertidos, "src/main/java/org/example/Data/monumentosEuskadiConvertidos.json");
    }
}