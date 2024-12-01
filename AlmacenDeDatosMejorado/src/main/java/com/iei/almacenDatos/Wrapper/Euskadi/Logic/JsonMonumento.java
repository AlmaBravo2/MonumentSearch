package com.iei.almacenDatos.Wrapper.Euskadi.Logic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iei.almacenDatos.Wrapper.Euskadi.Models.MonumentoConvertidoEuskadi;
import com.iei.almacenDatos.Wrapper.Euskadi.Models.MonumentoOrginalEuskadi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonMonumento {



   public static List<MonumentoOrginalEuskadi> readJson(String filePath) {
       ObjectMapper objectMapper = new ObjectMapper();
       objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       File file = new File(filePath);



       List<MonumentoOrginalEuskadi> monumentos = new ArrayList<>();
       try {
           String jsonContent = readFileAsString(filePath);

           String cleanedJson = jsonContent.replaceAll("\"address\"\\s*:\\s*\"\",?", "");
           monumentos = objectMapper.readValue(cleanedJson, new TypeReference<List<MonumentoOrginalEuskadi>>() {});
       } catch (IOException e) {
           e.printStackTrace();
       }
       return monumentos;

    }

    private static String readFileAsString(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString();
    }



    public static String writeJson(List<MonumentoConvertidoEuskadi> monumentos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(monumentos);

    }
}