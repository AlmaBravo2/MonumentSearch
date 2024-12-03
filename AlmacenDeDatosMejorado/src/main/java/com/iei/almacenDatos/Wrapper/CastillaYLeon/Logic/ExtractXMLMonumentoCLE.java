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

        //CREAMOS UNA LISTA DONDE ALMACENAREMOS LOS MONUMENTOS CONVERTIDOS AL MODELO JAVA
        List<Monumento> monumentos = new ArrayList<>();

        try {
            // CREAMOS UN DOCUMENTBUILDER, QUE SERVIRÁ PARA ANALIZAR EL XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // SE LEE EL ARCHIVO XML
            Document doc = builder.parse(new File(filePath));
            doc.getDocumentElement().normalize();

            // SE OBTIENEN TODOS LOS NODOS <MONUMENTO> DEL XML EN UNA LISTA
            NodeList nodeList = doc.getElementsByTagName("monumento");

            //RECORREMOS LA NODELIST PASANDO POR CADA MONUMENTO DE LA LISTA
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);
                //SE VERIFICA QUE EL NODO ACTUAL ES UN ELEMENTO XML VÁLIDO Y
                // SE CONVIERTE A ELEMENT, LO QUE PERMITE ACCEDER DIRECTAMENTE AL CONTENIDO DEL ELEMENTO XML
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    //CREAMOS NUEVO OBJETO 'MONUMENTO' DONDE ALMACENAREMOS LOS DATOS EXTRAÍDOS DEL XML DE ESE MONUMENTO
                    Monumento monumento = new Monumento();

                    // ELEMENTO NOMBRE
                    monumento.setNombre(getTagValue("nombre", element));

                    // ELEMENTO COORDENADAS, QUE CONTIENE LATITUD Y LONGITUD
                    Element coordenadas = null;
                    NodeList coordenadasNodes = element.getElementsByTagName("coordenadas");

                    if (coordenadasNodes != null && coordenadasNodes.getLength() > 0) {
                        //OBTIENE EL NODO 'COORDENADAS' DEL MONUMENTO
                        coordenadas = (Element) coordenadasNodes.item(0);
                        //SI LOS VALORES DE LATITUD Y LONGITUD NO SON NULOS NI ESTÁN VACÍOS,
                        //SE LIMPIA SU FORMATO POR SI ACASO HAY ALGUNA ERRATA Y SE ASIGNAN
                        if((getTagValue("latitud", coordenadas) != null && !getTagValue("latitud", coordenadas).isEmpty() ) && (getTagValue("longitud", coordenadas) != null && !getTagValue("longitud", coordenadas).isEmpty())){
                            String latitud = getTagValue("latitud", coordenadas);
                            String longitud = getTagValue("longitud", coordenadas);

                            latitud = validateAndCleanCoordinate(latitud);
                            longitud = validateAndCleanCoordinate(longitud);

                            monumento.setLatitud(latitud);
                            monumento.setLongitud(longitud);
                        }else{
                            //EN CASO DE QUE ALGUNA DE LAS COORDENADAS SEA NULA O ESTÉ VACÍA,
                            //SE OBTIENEN LAS COORDENADAS A TRAVÉS DEL NOMBRE DEL MONUMENTO
                            HashMap<String,String> coordenadasAPI = getCoordinates(getTagValue("nombre", element));
                            String longitudAPI = coordenadasAPI.get("longitud");
                            String latitudAPI = coordenadasAPI.get("latitud");
                            monumento.setLongitud(longitudAPI);
                            monumento.setLatitud(latitudAPI);
                        }
                    }

                    // ELEMENTO TIPO: CONVERTIMOS LOS TIPOS DE MONUMENTOS
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

                    //ELEMENTO DESCRIPCION
                    String descrip = getTagValue("Descripcion", element);
                    if (descrip != null) {
                        // LIMPIAMOS EL FORMATO ELIMINANDO ETIQUETAS HTML CON LA BIBLIOTECA JSOUP,
                        //QUE SIRVE PARA ANALIZAR Y MANIPULAR DATOS HTML
                        String sinHtml = Jsoup.parse(descrip).text();

                        //SE USA LA BIBLIOTECA APACHE COMMONS TEXT PARA DECODIFICAR CUALQUIER CARACTER
                        //ESPECIAL CODIFICADO EN FORMATO HTML
                        String convertirCaract = StringEscapeUtils.unescapeHtml4(sinHtml);
                        monumento.setDescripcion(convertirCaract);
                    } else {
                        //SI EL MONUMENTO NO TIENE DESCRIPCIÓN, PONEMOS COMO DESCRIPCIÓN EL TIPO DE MONUMENTO
                        String info = monumento.getTipo();
                        monumento.setDescripcion(info);
                    }

                    //ELEMENTO POBLACIÓN, QUE CONTIENE PROVINCIA Y LOCALIDAD
                    Element poblacion = null;
                    NodeList poblacionNodes = element.getElementsByTagName("poblacion");
                    if (poblacionNodes != null && poblacionNodes.getLength() > 0) {
                        //OBTIENE EL NODO 'POBLACION' DEL MONUMENTO
                        poblacion = (Element) poblacionNodes.item(0);
                        monumento.setProvincia(getTagValue("provincia", poblacion));
                        monumento.setLocalidad(getTagValue("localidad", poblacion));
                    }

                    //ELEMENTO DIRECCION
                    String calle;
                    String calleNodes = getTagValue("direccion", element);
                    if(calleNodes != null){
                        calle = calleNodes;
                    } else {
                        //SI LA DIRECCION ES NULL, OBTENEMOS LA DIRECCION A PARTIR DE LAS COORDENADAS
                        calle = MonumentLocator.getMonumentDirection(monumento.getLongitud(),monumento.getLatitud());
                    }
                    monumento.setDireccion(calle);

                    //ELEMENTO CÓDIGO POSTAL
                    String codPostal = getTagValue("codigoPostal", element);
                    if (codPostal == null || codPostal.isEmpty()) {
                        //SI EL CODIGO POSTAL ES NULL, SE OBTIENE A PARTIR DE LAS COORDENADAS
                        String latitud = monumento.getLatitud();
                        String longitud = monumento.getLongitud();
                        String codigoPostalAPI = MonumentLocator.getMonumentPostCode(longitud,latitud);
                        monumento.setCodigo_postal(codigoPostalAPI);
                    }else {
                        //SI NO ES NULL, COMPROBAMOS QUE EL CÓDIGO COINCIDE CORRECTAMENTE CON LA PROVINCIA
                        String codPostalRefactor = comparisonPostalCode(monumento.getProvincia(),codPostal);
                        monumento.setCodigo_postal(codPostalRefactor);
                    }
                    // AÑADIMOS EL MONUMENTO A LA LISTA
                    monumentos.add(monumento);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return monumentos;
    }

    //MÉTODO PARA OBTENER EL CONTENIDO DE UN NODO XML
    private static String getTagValue(String tagName, Element element) {

        if (element == null) {
            return null;
        }
        //SE OBTIENE UNA LISTA DE LOS NODOS QUE COINCIDEN CON EL NOMBRE DE LA ETIQUETA 'tagName'
        //EN ESE ELEMENTO MONUMENTO
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                //SE EXTRAE EL CONTENIDO DEL NODO, QUE SERÁ LA INFORMACIÓN DESEADA
                return node.getTextContent();
            }
        }
        return null;
    }

    //MÉTODO USADO PARA CONVERTIR LOS TIPOS DE MONUMENTO
    //SE COMPRUEBA SI EL CONTENIDO DE LA ETIQUETA 'TIPOMONUMENTO' CONTIENE EL TIPO QUE SE PASA COMO ARGUMENTO
    private static boolean searchTipo(String tipo, Element element){
        return Objects.requireNonNull(getTagValue("tipoMonumento", element)).contains(tipo);
    }

    //MÉTODO PARA RELACIÓN PROVINCIA-CÓDIGO POSTAL
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
