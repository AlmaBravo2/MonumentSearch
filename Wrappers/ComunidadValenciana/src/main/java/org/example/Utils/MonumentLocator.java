package org.example.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class MonumentLocator {

    public static HttpResponse<String> getMonumentLocationInfo(String longitude, String latitude){
        try {
            // Construir la URL para la solicitud a Nominatim
            String url = String.format(
                    "https://nominatim.openstreetmap.org/reverse?format=json&lat=%s&lon=%s&addressdetails=1&zoom=100",
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
            return auxPostCodeObtainer(longitude, latitude);
        }
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

    public static HashMap<String,String> getCoordinates(String monumentName){
        HashMap<String,String> coordinates = new HashMap<>();
        try {


            // Construir la URL para la solicitud a Nominatim
            String url = String.format(
                    "https://nominatim.openstreetmap.org/search?q=%s&format=json",
                    monumentName
            );

            // Crear el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());

            coordinates.put("latitud",rootNode.get(0).get("lat").asText());
            coordinates.put("longitud",rootNode.get(0).get("lon").asText());
        } catch (Exception e) {
            coordinates.put("latitud",null);
            coordinates.put("longitud",null);
        }
        return coordinates;
    }


    public static String auxPostCodeObtainer(String longitude, String latitude){
        String url = String.format(
                "http://api.geonames.org/findNearbyPostalCodesJSON?lat=%s&lng=%s&username=saboravr",
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
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            return rootNode.get("postalCodes").get(0).get("postalCode").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
