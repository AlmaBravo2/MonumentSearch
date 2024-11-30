package org.example;

import org.example.Models.MonumentoConvertido;
import org.example.Models.MonumentoOriginal;
import org.example.Utils.TextSanitizer;
import org.w3c.dom.Text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Clase para leer archivos CSV y obtener los monumentos descritos en él.
public class CSVMonumento {

    /*
    LEE UN ARCHIVO CSV. DEVUELVE UNA LISTA (CADA LINEA) DE LISTAS DE STRING (TODOS LOS ELEMENTOS QUE TIENE UNA LINEA
    SEPARADOS)
    */
    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < values.length; i++) {
                    values[i] = TextSanitizer.accentMarkFixerCodification(values[i]);
                }
                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    /*
    * LO MISMO QUE EL MÉTODO ANTERIOR PERO RECIBE UN STRING EN VEZ DE UNA RUTA DE ARCHIVO
    * */
    public static List<List<String>> readCSVFromString(String input){
        List<List<String>> records = new ArrayList<>();
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] values = line.split(";");
            for (int i = 0; i < values.length; i++) {
                values[i] = TextSanitizer.accentMarkFixerCodification(values[i]);
            }
            records.add(Arrays.asList(values));
        }
        return records;
    }


    /*  MÉTODO QUE OBTIENE EL VALOR DEL ELEMENTO i en una linea del CSV */
    private static String getValue(List<?> record, int index) {
        if (index < record.size() && record.get(index) != null) {
            String value = (String) record.get(index);
            return value.isEmpty() ? "" : value;
        }
        return "";
    }

     /* MÉTODO QUE COGE UNA LISTA Y CON EL MÉTODO ANTERIOR VA SACANDO LOS VALORES PARA CREAR UN MONUMENTO
     * ¡¡ SIN MODIFICAR AUN LAS COORDENADAS!!
     * */
    public static MonumentoOriginal convertToMonumento(List<?> record) {
        return new MonumentoOriginal(
                (String) getValue(record, 0),
                (String) getValue(record, 1),
                (String) getValue(record, 2),
                (String) getValue(record, 3),
                (String) getValue(record, 4),
                (String) getValue(record, 5),
                (String) getValue(record, 6),
                (String) getValue(record, 7),
                (String) getValue(record, 8),
                (String) getValue(record, 9)
        );
    }



    /* MÉTODO QUE DEVUELVE UNA LISTA DE MONUMENTOS ORIGINALES A PARTIR DE UN ARCHIVO CSV.
    * HACE USO DE LOS MÉTODOS ANTERIORES PARA LEER EL ARCHIVO Y CONVERTIR CADA LINEA EN UN MONUMENTO
    *  */
    public static List<MonumentoOriginal> getMonumentos(String filePath) {
        List<List<String>> records = readCSV(filePath);
        List<MonumentoOriginal> monumentos = new ArrayList<>();
        for (List<?> record : records) {
            monumentos.add(convertToMonumento(record));
        }
        return monumentos;
    }


}
