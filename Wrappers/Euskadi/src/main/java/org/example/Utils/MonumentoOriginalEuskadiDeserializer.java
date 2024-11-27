package org.example.Utils;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import org.example.Models.MonumentoOrginalEuskadi;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.text.Element;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonumentoOriginalEuskadiDeserializer implements JsonDeserializer<MonumentoOrginalEuskadi> {

    private Map<String, List<String>> keyValuesMap = new HashMap<String, List<String>>();

    @Override
    public MonumentoOrginalEuskadi deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Obtener los campos, verificando si existen


        String postalCode = jsonObject.has("postalCode") ? jsonObject.get("postalCode").getAsString() : "";

        String documentName = jsonObject.has("documentName") ? jsonObject.get("documentName").getAsString() : "";
        String documentDescription = jsonObject.has("documentDescription") ? jsonObject.get("documentDescription").getAsString() : "";
        String municipality = jsonObject.has("municipality") ? jsonObject.get("municipality").getAsString() : "";
        String address = extractAddress(json.toString());
        String latwgs84 = jsonObject.has("latwgs84") ? jsonObject.get("latwgs84").getAsString() : "";
        String lonwgs84 = jsonObject.has("lonwgs84") ? jsonObject.get("lonwgs84").getAsString() : "";
        String territory = jsonObject.has("territory") ? jsonObject.get("territory").getAsString() : "";

        // Crear el objeto
        return new MonumentoOrginalEuskadi(documentName, documentDescription, municipality, address, latwgs84, lonwgs84, territory, postalCode);
    }

    public static String extractAddress(String jsonString) {
        // Define el patrón para buscar la clave "address" y capturar el valor entre comillas
        String regex = "\"address\"\\s*:\\s*\"(.*?)\"";

        // Compila el patrón
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(jsonString);

        // Busca la primera coincidencia
        if (matcher.find()) {
            return matcher.group(1); // Retorna el valor capturado entre comillas
        }

        // Si no se encuentra, retorna un valor vacío o mensaje
        return "Address no encontrado";
    }

    @JsonAnyGetter
    public Map<String, List<String>> getKeyValuesMap() {
        return keyValuesMap;
    }

    @JsonAnySetter
    public void duplicateKeyValues(String key, String value) {
        List<String> values = null;
        if (!keyValuesMap.containsKey(key)) {
            values = new ArrayList<String>();
        } else {
            values = keyValuesMap.get(key);
        }
        values.add(value);
        keyValuesMap.put(key, values);
    }


}