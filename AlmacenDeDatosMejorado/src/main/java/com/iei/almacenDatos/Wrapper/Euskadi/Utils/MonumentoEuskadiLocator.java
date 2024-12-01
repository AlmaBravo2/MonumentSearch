package com.iei.almacenDatos.Wrapper.Euskadi.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MonumentoEuskadiLocator {

    public static HttpResponse<String> getMonumentLocationInfo(String longitude, String latitude){
        try {
            // Construir la URL para la solicitud a Nominatim
            String url = String.format(
                    "https://nominatim.openstreetmap.org/reverse?format=json&lat=%s&lon=%s",
                    latitude,
                    longitude
            );

            // Crear el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
    public static String getMonumentDirection(String longitude, String latitude) {
        HttpResponse<String> response = getMonumentLocationInfo(longitude, latitude);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            return rootNode.get("display_name").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMonumentPostCode(String longitude,String latitude){
        HttpResponse<String> response = getMonumentLocationInfo(longitude, latitude);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            return rootNode.get("address").get("postcode").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMonumentCity(String longitude, String latitude){
        HttpResponse<String> response = getMonumentLocationInfo(longitude, latitude);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            if(rootNode.get("address").get("city") != null){
                return rootNode.get("address").get("city").asText();
            }else{
                return rootNode.get("address").get("town").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMonumentProvince(String longitude, String latitude){
        HttpResponse<String> response = getMonumentLocationInfo(longitude, latitude);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            return rootNode.get("address").get("state").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
