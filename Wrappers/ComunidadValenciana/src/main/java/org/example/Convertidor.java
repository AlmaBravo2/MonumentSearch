package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Models.*;
import org.example.Utils.MonumentLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.example.CSVMonumento.getMonumentos;

public class Convertidor {


    public static List<MonumentoConvertido> convertir() {
        return null;
    }

    private static Monumento convertirMonumento(MonumentoConvertido monumento) {
        String nombre = monumento.getDenominacion();
        String tipo = null;
        String provinciaNombre = monumento.getProvincia();
        String municipio = monumento.getMunicipio();
        String latitud = monumento.getLatitud();
        String longitud = monumento.getLongitud();
        String descripcion = null;
        String codPostal = MonumentLocator.getMonumentPostCode(latitud, longitud);
        Provincia provincia = new Provincia(codPostal.substring(0, 2), provinciaNombre);
        Localidad localidad = new Localidad(municipio);
        return new Monumento(nombre, tipo, null, codPostal, longitud, latitud, descripcion, localidad, provincia);
    }


    public static void main(String[] args) throws JsonProcessingException {
        List<MonumentoConvertido> monumentos = convertir();
        for (MonumentoConvertido monumento : monumentos) {
            ObjectMapper objectMapper = new ObjectMapper();
           Monumento monumentoConvertido = convertirMonumento(monumento);
           System.out.println(objectMapper.writeValueAsString(monumentoConvertido));
        }
    }
}
