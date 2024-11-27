package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.example.Models.MonumentoConvertidoEuskadi;
import org.example.Models.MonumentoOrginalEuskadi;

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



    public static void writeJson(List<MonumentoConvertidoEuskadi> monumentos, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath), "ISO-8859-1")) {
            gson.toJson(monumentos, writer);
            System.out.println("Datos escritos en " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}