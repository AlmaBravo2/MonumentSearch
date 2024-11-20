package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVMonumento {

    public static List<List<?>> readCSV(String filePath) {
        List<List<?>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "ISO-8859-1"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
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
        List<List<?>> records = readCSV(filePath);
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
    }
}
