package org.example;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.example.Utils.MonumentoOriginalEuskadiDeserializer;

public class JsonMonumento {



   public static List<MonumentoOrginalEuskadi> readJson(String filePath) {

        try{
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeNulls();
            gsonBuilder.registerTypeAdapter(MonumentoOrginalEuskadi.class, new MonumentoOriginalEuskadiDeserializer());
            Gson gson = gsonBuilder.create();

        Type monumentoListType = new TypeToken<List<MonumentoOrginalEuskadi>>(){}.getType();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {


            return gson.fromJson(br, monumentoListType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }}
        catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

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