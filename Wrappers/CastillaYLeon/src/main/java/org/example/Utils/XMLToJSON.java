package org.example.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.Models.MonumentoOriginalCLE;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLToJSON {

    public static void main(String[] args) {

       /* try{
            XmlMapper xmlMapper = new XmlMapper();

            File xmlFile = new File("src/main/java/org/example/Data/monumentos.xml");
            ListaMonumentos listaMonumentos = xmlMapper.readValue(xmlFile, ListaMonumentos.class);

            ObjectMapper objectMapper = new ObjectMapper();

            String jsonResult = objectMapper.writeValueAsString(listaMonumentos.getMonumentos());
            System.out.println(jsonResult);


        } catch (IOException e){
            e.printStackTrace();
        }*/

        try{
            String xmlFile = "src/main/java/org/example/Data/monumentosEntrega.xml";
            List<Monumento> monumentos = ExtractXMLMonumentoCLE.readXML(xmlFile);

            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(monumentos);
            System.out.println(jsonString);

            File outputFile = new File("monumentosPruebaEntrega.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, monumentos);

            System.out.println("JSON generado y guardado en: " + outputFile.getAbsolutePath());

        }catch (IOException e) {
            e.printStackTrace();

        }

    }
}
