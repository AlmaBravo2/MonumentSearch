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
            List<Monumento> monumentos = com.iei.almacenDatos.Wrapper.CastillaYLeon.Logic.ExtractXMLMonumentoCLE.readXML(filePath);

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(monumentos);
            return jsonString;

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }
}
