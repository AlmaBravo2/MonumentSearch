package com.iei.almacenDatos.Wrapper.CastillaYLeon.Logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.iei.almacenDatos.Wrapper.CastillaYLeon.Models.Monumento;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLToJSON {

    public static String CLEToJSON(String filePath) {


        try {
            //SE CREA UNA LISTA DE MONUMENTOS A PARTIR DEL XML
            List<Monumento> monumentos = com.iei.almacenDatos.Wrapper.CastillaYLeon.Logic.ExtractXMLMonumentoCLE.readXML(filePath);

            //OBJECTMAPPER ES UNA CLASE DE LA BIBLIOTECA JACKSON, QUE SIRVE PARA LA CONVERSIÓN ENTRE OBJETOS JAVA Y OTROS FORMATOS COMO XML Y JSON
            //SE CONVIERTE LA LISTA DE MONUMENTOS EN UNA CADENA JSON Y SE CONVIERTE A UNA ESTRUCTURA LEGIBLE
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(monumentos);
            return jsonString;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
