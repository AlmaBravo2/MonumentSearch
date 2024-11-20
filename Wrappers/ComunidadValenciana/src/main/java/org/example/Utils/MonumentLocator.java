package org.example.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MonumentLocator {

    public static HttpResponse<String> getMonumentLocationInfo(double longitude, double latitude){
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
    public static String getMonumentDirection(double longitude, double latitude) {
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

    public static String getMonumentPostCode(double longitude, double latitude){
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

    public static String getMonumentCity(double longitude, double latitude){
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

    public static String getMonumentProvince(double longitude, double latitude){
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
