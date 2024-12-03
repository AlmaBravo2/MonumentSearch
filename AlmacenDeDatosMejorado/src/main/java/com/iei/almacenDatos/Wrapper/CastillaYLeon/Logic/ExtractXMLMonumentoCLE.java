package com.iei.almacenDatos.Wrapper.CastillaYLeon.Logic;

import com.iei.almacenDatos.Wrapper.CastillaYLeon.Models.Monumento;
import com.iei.almacenDatos.Wrapper.CastillaYLeon.Utils.*;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

import static com.iei.almacenDatos.Wrapper.CastillaYLeon.Utils.CleanCoord.validateAndCleanCoordinate;
import static com.iei.almacenDatos.Wrapper.CastillaYLeon.Utils.MonumentLocator.getCoordinates;


public class ExtractXMLMonumentoCLE {

    public static List<Monumento> readXML(String filePath) {
        List<Monumento> monumentos = new ArrayList<>();

        try {
            // Crear el DocumentBuilder para procesar el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leer el archivo XML
            Document doc = builder.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            // Obtener todos los nodos <monumento>
            NodeList nodeList = doc.getElementsByTagName("monumento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Monumento monumento = new Monumento();

                    // NOMBRE
                    monumento.setNombre(getTagValue("nombre", element));

                    // Extraer datos de <coordenadas>
                    Element coordenadas = null;
                    NodeList coordenadasNodes = element.getElementsByTagName("coordenadas");
                    if (coordenadasNodes != null && coordenadasNodes.getLength() > 0) {
                        //Element coordenadas = (Element) element.getElementsByTagName("coordenadas").item(0);
                        coordenadas = (Element) coordenadasNodes.item(0);
                        //Monumento.Coordenadas coord = new Monumento.Coordenadas();
                        if((getTagValue("latitud", coordenadas) != null && !getTagValue("latitud", coordenadas).isEmpty() ) && (getTagValue("longitud", coordenadas) != null && !getTagValue("longitud", coordenadas).isEmpty())){
                            String latitud = getTagValue("latitud", coordenadas);
                            String longitud = getTagValue("longitud", coordenadas);

                            latitud = validateAndCleanCoordinate(latitud);
                            longitud = validateAndCleanCoordinate(longitud);

                            monumento.setLatitud(latitud);
                            monumento.setLongitud(longitud);


                        }else{
                            HashMap<String,String> coordenadasAPI = getCoordinates(getTagValue("nombre", element));
                            String longitudAPI = coordenadasAPI.get("longitud");
                            String latitudAPI = coordenadasAPI.get("latitud");
                            monumento.setLongitud(longitudAPI);
                            monumento.setLatitud(latitudAPI);
                        }
                        //monumento.setLatitud(getTagValue("latitud", coordenadas));
                        //monumento.setLongitud(getTagValue("longitud", coordenadas));
                        //monumento.setCoordenadas(coord);
                    }

                    // TIPO DE MONUMENTO: CONVERTIMOS LOS TIPOS
                    if(searchTipo("Yacimientos arqueológicos", element)){
                        monumento.setTipo("Yacimiento arqueológico");
                    }else if(searchTipo("Catedrales", element) || searchTipo("Iglesias y ermitas", element)
                                || searchTipo("Santuarios",element) || searchTipo("Cruceros",element)){
                        monumento.setTipo("Iglesia-Ermita");
                    }else if(searchTipo("Monasterios",element)){
                        monumento.setTipo("Monasterio-Convento");
                    }else if(searchTipo("Castillos",element) || searchTipo("Palacios",element)
                                || searchTipo("Casas nobles",element) || searchTipo("Murallas y puertas",element)
                                || searchTipo("Torres",element)){
                        monumento.setTipo("Castillo-Fortaleza-Torre");
                    }else if(searchTipo("Reales sitios",element)){
                        monumento.setTipo("Edificio singular");
                    }else if(searchTipo("Puentes",element)){
                        monumento.setTipo("Puente");
                    }else{
                        monumento.setTipo("Otros");
                    }

                    String descrip = getTagValue("Descripcion", element);
                    if (descrip != null) {
                        // LIMPIAMOS EL FORMATO
                        String sinHtml = Jsoup.parse(descrip).text();
                        String convertirCaract = StringEscapeUtils.unescapeHtml4(sinHtml);
                        monumento.setDescripcion(convertirCaract);
                    } else {
                        /*HttpResponse<String> respuestaAPIInfo = MonumentLocator.getMonumentLocationInfo(monumento.getLongitud(),monumento.getLatitud());
                        assert respuestaAPIInfo != null;
                        String info = respuestaAPIInfo.toString();*/
                        String info = monumento.getTipo();
                        monumento.setDescripcion(info);
                    }

                    // DATOS DE POBLACIÓN: PROVINCIA, MUNICIPIO, LOCALIDAD
                    Element poblacion = null;
                    NodeList poblacionNodes = element.getElementsByTagName("poblacion");
                    if (poblacionNodes != null && poblacionNodes.getLength() > 0) {
                        //poblacion = (Element) element.getElementsByTagName("poblacion").item(0);
                        poblacion = (Element) poblacionNodes.item(0);
                       // Monumento.Poblacion pob = new Monumento.Poblacion();
                        monumento.setProvincia(getTagValue("provincia", poblacion));
                        //monumento.setMunicipio(getTagValue("municipio", poblacion));
                        monumento.setLocalidad(getTagValue("localidad", poblacion));
                        //monumento.setPoblacion(pob);
                    }

                    // CALLE
                    String calle;
                    String calleNodes = getTagValue("direccion", element);
                    if(calleNodes != null){
                        calle = calleNodes;
                    } else {
                        calle = MonumentLocator.getMonumentDirection(monumento.getLongitud(),monumento.getLatitud());
                        //calle = "desconocido";
                    }
                    monumento.setDireccion(calle);
                    /*ANTERIOR BIEN String calle = getTagValue("calle", element);
                    if (calle == null || calle.isEmpty()) {
                        // Llamar a MonumentLocator.getMonumentDirection(latitud, longitud) si calle es null
                        String latitud = monumento.getLatitud();
                        String longitud = monumento.getLongitud();
                        if (latitud != null && longitud != null) {
                            calle = MonumentLocator.getMonumentDirection(latitud, longitud);
                        }else {
                            System.err.println("No se puede obtener la dirección porque las coordenadas son nulas para este monumento.");
                            calle = "Dirección desconocida"; // Valor predeterminado
                        }
                    }*/


                    //monumento.setCalle(getTagValue("calle", element));

                    // CÓDIGO POSTAL
                    String codPostal = getTagValue("codigoPostal", element);
                    if (codPostal == null || codPostal.isEmpty()) {
                        // Llamar a MonumentLocator.getMonumentDirection(latitud, longitud) si calle es null
                        String latitud = monumento.getLatitud();
                        String longitud = monumento.getLongitud();
                        /*if (latitud != null && longitud != null) {
                            codPostal = MonumentLocator.getMonumentPostCode(latitud, longitud);
                        }*/
                        String codigoPostalAPI = MonumentLocator.getMonumentPostCode(longitud,latitud);
                        monumento.setCodigo_postal(codigoPostalAPI);
                    }else {
                        String codPostalRefactor = comparisonPostalCode(monumento.getProvincia(),codPostal);
                        monumento.setCodigo_postal(codPostalRefactor);
                    }
                    // AÑADIMOS MONUMENTO A LA LISTA
                    monumentos.add(monumento);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monumentos;
    }

    // Método auxiliar para obtener el texto de un tag
    private static String getTagValue(String tagName, Element element) {

        if (element == null) {
            return null;
        }

        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                return node.getTextContent();
            }
        }
        return null;
    }

    private static boolean searchTipo(String tipo, Element element){
        return Objects.requireNonNull(getTagValue("tipoMonumento", element)).contains(tipo);
    }

    public static String comparisonPostalCode(String provincia, String postalCode) {
        Map<String, String> postalCodesProvincias = new HashMap<>();
        postalCodesProvincias.put("24", "León");
        postalCodesProvincias.put("49", "Zamora");
        postalCodesProvincias.put("37", "Salamanca");
        postalCodesProvincias.put("34", "Palencia");
        postalCodesProvincias.put("47", "Valladolid");
        postalCodesProvincias.put("05", "Ávila");
        postalCodesProvincias.put("09", "Burgos");
        postalCodesProvincias.put("42", "Soria");
        postalCodesProvincias.put("40", "Segovia");

        String provinciaCode = postalCode.substring(0, 2);

        if (postalCodesProvincias.containsKey(provinciaCode) && postalCodesProvincias.get(provinciaCode).equalsIgnoreCase(provincia)) {
            return postalCode;
        }
        else{
            for(Map.Entry<String, String> entry : postalCodesProvincias.entrySet()){
                if(entry.getValue().equalsIgnoreCase(provincia)){
                    return entry.getKey() + postalCode.substring(2);
                }
            }

        }

        return null;
    }

}
