package org.example;

import org.example.Models.MonumentoConvertido;
import org.example.Models.MonumentoOriginal;
import org.w3c.dom.Text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVMonumento {

    public static List<List<String>> readCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < values.length; i++) {
                    byte[] bytes = values[i].getBytes(StandardCharsets.ISO_8859_1);
                    String decoded =  new String(bytes, StandardCharsets.UTF_8);
                    values[i] = decoded;

                }

                records.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }



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
    
    private static String getValue(List<?> record, int index) {
        if (index < record.size() && record.get(index) != null) {
            String value = (String) record.get(index);
            return value.isEmpty() ? "" : value;
        }
        return "";
    }

    
    public static List<MonumentoOriginal> getMonumentos(String filePath) {
        List<List<String>> records = readCSV(filePath);
        List<MonumentoOriginal> monumentos = new ArrayList<>();
        for (List<?> record : records) {
            monumentos.add(convertToMonumento(record));
        }
        return monumentos;
    }

    public static void writeCSV(List<MonumentoConvertido> monumentos, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("IGPCV;DENOMINACION;PROVINCIA;MUNICIPIO;LATITUD;LONGITUD;CODCLASIFICACION;CLASIFICACION;CODCATEGORIA;CATEGORIA\n");
            for (MonumentoConvertido monumento : monumentos) {
                bw.write(monumento.getIgpcv() + ";" + monumento.getDenominacion() + ";" + monumento.getProvincia() + ";" + monumento.getMunicipio() + ";" + monumento.getLatitud() + ";" + monumento.getLongitud() + ";" + monumento.getCodclasificacion() + ";" + monumento.getClasificacion() + ";" + monumento.getCodcategoria() + ";" + monumento.getCategoria() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<List<String>> records = readCSV("src/main/java/org/example/Data/monumentos.csv");
        for (List<?> record : records) {
            System.out.println(record);
        }

    }
}
