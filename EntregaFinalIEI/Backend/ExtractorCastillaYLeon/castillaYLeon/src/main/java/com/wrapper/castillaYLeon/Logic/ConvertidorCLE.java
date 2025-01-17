package com.wrapper.castillaYLeon.Logic;

import com.wrapper.castillaYLeon.Models.*;
import com.wrapper.castillaYLeon.Utils.LevenshteinComparator;
import com.wrapper.castillaYLeon.Utils.MonumentLocator;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.text.StringEscapeUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static com.wrapper.castillaYLeon.Utils.CleanCoord.validateAndCleanCoordinate;
import static com.wrapper.castillaYLeon.Utils.MonumentLocator.getCoordinates;


public class ConvertidorCLE {
    private String informe = "<-------------------CASTILLA Y LEÓN---------------------->\n";
    private HashMap<String,String> rechazados = new HashMap<>();
    private HashMap<String,String> modificados = new HashMap<>();
    private int contadorDeCorrectos = 0;
    public ConvertidorCLE() {
        // Inicialización si es necesario
    }

    public Map<String, String> getModificados() {
        return modificados;
    }

    public Map<String, String> getRechazados() {
        return rechazados;
    }
    public List<MonumentoCLE> readXML(String filePath) {

        //CREAMOS UNA LISTA DONDE ALMACENAREMOS LOS MONUMENTOS CONVERTIDOS AL MODELO JAVA
        List<MonumentoCLE> monumentos = new ArrayList<>();

        try {

            InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
            if (is == null) {
                throw new FileNotFoundException("No se encontró el archivo en el classpath: " + filePath);
            }

            // CREAMOS UN DOCUMENTBUILDER, QUE SERVIRÁ PARA ANALIZAR EL XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(false); // No ignorar espacios en blanco
            factory.setCoalescing(true); // Combinar nodos de texto adyacentes
            factory.setNamespaceAware(true); // Manejar nombres con espacios de nombres
            DocumentBuilder builder = factory.newDocumentBuilder();

            // SE LEE EL ARCHIVO XML
            Document doc = builder.parse(is);
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
                    MonumentoCLE monumento = new MonumentoCLE();

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
                            String latitud = getTagValue("longitud", coordenadas);
                            String longitud = getTagValue("latitud", coordenadas);

                            latitud = validateAndCleanCoordinate(latitud);
                            longitud = validateAndCleanCoordinate(longitud);

                            monumento.setLatitud(latitud);
                            monumento.setLongitud(longitud);
                        }else{
                            //EN CASO DE QUE ALGUNA DE LAS COORDENADAS SEA NULA O ESTÉ VACÍA,
                            //SE RECHAZA EL MONUMENTO
                            rechazados.put(monumento.getNombre(),"No tiene coordenadas o alguna de ellas");
                            continue;
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
                        modificados.put(monumento.getNombre(), "Se ha añadido como descripción el tipo de monumento");
                        monumento.setDescripcion(info);
                    }

                    //ELEMENTO POBLACIÓN, QUE CONTIENE PROVINCIA Y LOCALIDAD
                    Element poblacion = null;
                    NodeList poblacionNodes = element.getElementsByTagName("poblacion");
                    if (poblacionNodes != null && poblacionNodes.getLength() > 0) {
                        //OBTIENE EL NODO 'POBLACION' DEL MONUMENTO
                        poblacion = (Element) poblacionNodes.item(0);
                        String provincia1 = getTagValue("provincia", poblacion);
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Segovia") < 3 &&
                                !provincia1.equals("Segovia")) {
                            provincia1 = "Segovia";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                            System.out.println("Modificación en provincia para: " + monumento.getNombre()); // Depuración
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"León") < 3 &&
                                !provincia1.equals("León")) {
                            provincia1 = "León";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Zamora") < 3 &&
                                !provincia1.equals("Zamora")) {
                            provincia1 = "Zamora";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Salamanca") < 3 &&
                                !provincia1.equals("Salamanca")) {
                            provincia1 = "Salamanca";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Palencia") < 3 &&
                                !provincia1.equals("Palencia")) {
                            provincia1 = "Palencia";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Valladolid") < 3 &&
                                !provincia1.equals("Valladolid")) {
                            provincia1 = "Valladolid";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Ávila") < 3 &&
                                !provincia1.equals("Ávila")) {
                            provincia1 = "Ávila";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Burgos") < 3 &&
                                !provincia1.equals("Burgos")) {
                            provincia1 = "Burgos";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }
                        if(LevenshteinComparator.calculateLevenshteinDistance(provincia1,"Soria") < 3 &&
                                !provincia1.equals("Soria")) {
                            provincia1 = "Soria";
                            modificados.put(monumento.getNombre(),"Error tipográfico en el nombre de la provincia");
                        }


                        monumento.setProvincia(provincia1);
                        monumento.setLocalidad(getTagValue("localidad", poblacion));
                    }

                    //ELEMENTO DIRECCION
                    String calle;
                    String calleNodes = getTagValue("direccion", element);
                    if(calleNodes != null){
                        calle = calleNodes;
                        monumento.setDireccion(calle);
                    } else {
                        //SI LA DIRECCION ES NULL, OBTENEMOS LA DIRECCION A PARTIR DE LAS COORDENADAS
                        calle = MonumentLocator.getMonumentDirection(monumento.getLongitud(),monumento.getLatitud());
                        monumento.setDireccion(calle);
                        modificados.put(monumento.getNombre(), "Se ha obtenido la dirección a partir de las coordenadas");
                    }


                    //ELEMENTO CÓDIGO POSTAL
                    String codPostal = getTagValue("codigoPostal", element);

                    if (codPostal == null || codPostal.isEmpty()) {
                        //SI EL CODIGO POSTAL ES NULL, SE OBTIENE A PARTIR DE LAS COORDENADAS
                        String latitud = monumento.getLatitud();
                        String longitud = monumento.getLongitud();
                        String codigoPostalAPI = MonumentLocator.getMonumentPostCode(longitud,latitud);

                        if (codigoPostalAPI != null && !codigoPostalAPI.isEmpty()) {
                            monumento.setCodigo_postal(codigoPostalAPI);
                            modificados.put(monumento.getNombre(), "Se ha obtenido el código postal a partir de las coordenadas");
                        } else {
                            System.err.println("No se pudo obtener un código postal para las coordenadas: " + latitud + ", " + longitud);
                        }
                    }else {
                        //SI NO ES NULL, COMPROBAMOS QUE EL CÓDIGO COINCIDE CORRECTAMENTE CON LA PROVINCIA
                        HashMap<String,String> codPostalRefactor = comparisonPostalCode(monumento.getProvincia(),codPostal);
                        if (codPostalRefactor.isEmpty()) {
                            // Manejo de caso donde no se pudo encontrar ni modificar el código postal
                            System.out.println("No se encontró un código postal válido para la provincia: " + monumento.getProvincia());
                        }else {

                            if (codPostalRefactor.containsKey("Correcto")) {
                                String codPostalMod = codPostalRefactor.get("Correcto");
                                monumento.setCodigo_postal(codPostalMod);
                            } else {
                                monumento.setCodigo_postal(codPostalRefactor.get("Modificado"));
                                modificados.put(monumento.getNombre(), "Se ha corregido el nombre de provincia, que no era concordante con el código postal");
                            }
                        }
                    }

                    if (!modificados.containsKey(monumento.getNombre()) && !rechazados.containsKey(monumento.getNombre())) {
                        contadorDeCorrectos++; // Incrementa si el monumento no está en los modificados
                    }else {
                        if (modificados.containsKey(monumento.getNombre())) {
                            System.out.println("Monumento modificado: " + monumento.getNombre()); // Depuración
                        }
                        if (rechazados.containsKey(monumento.getNombre())) {
                            System.out.println("Monumento rechazado: " + monumento.getNombre()); // Depuración
                        }
                    }

                    // AÑADIMOS EL MONUMENTO A LA LISTA
                    monumentos.add(monumento);
                }
            }
            is.close();
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
            if (node != null) {
                StringBuilder value = new StringBuilder();
                // RECORREMOS TODOS LOS NODOS HIJOS PARA CONCATENAR EL TEXTO
                Node child = node.getFirstChild();
                while (child != null) {
                    if (child.getNodeType() == Node.TEXT_NODE || child.getNodeType() == Node.CDATA_SECTION_NODE) {
                        value.append(child.getTextContent().trim());
                    }
                    child = child.getNextSibling();
                }
                return value.toString();
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
    public static HashMap<String,String> comparisonPostalCode(String provincia, String postalCode) {
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
            HashMap<String,String> correcto = new HashMap<>();
            correcto.put("Correcto",postalCode);
            return correcto;
        }
        else{
            for(Map.Entry<String, String> entry : postalCodesProvincias.entrySet()){
                if(entry.getValue().equalsIgnoreCase(provincia)){
                    HashMap<String,String> modificado = new HashMap<>();
                    modificado.put("Modificado",entry.getKey() + postalCode.substring(2));
                    return modificado;
                }
            }

        }

        return new HashMap<>();
    }

    public MonumentosDTO getMonumentosCLE(String filepath){
        ConvertidorCLE convertidorCLE = new ConvertidorCLE();
        List<MonumentoCLE> monumentosObtenidos = convertidorCLE.readXML(filepath);

        Map<String, String> modificados = convertidorCLE.getModificados();
        Map<String, String> rechazados = convertidorCLE.getRechazados();

        int numModificados = modificados.size();
        int numRechazados = rechazados.size();

        informe += "Monumentos correctos: " + contadorDeCorrectos + "\n";
        informe += "Monumentos rechazados: " + numRechazados + " -> Desglose: " + rechazados + "\n";
        informe += "Monumentos modificados: " + numModificados + " -> Desglose: " + modificados + "\n";

        //Convertir MonumentoCLE a MonumentoDTO
        List<MonumentoCLE> monumentos = monumentosObtenidos.stream().distinct().collect(Collectors.toList());

        List<MonumentoDTO> monumentosDTO = new ArrayList<>();
        for (MonumentoCLE monumento : monumentos) {
            Localidad localidad = new Localidad();
            Provincia provincia = new Provincia();
            provincia.setNombre(monumento.getProvincia().toUpperCase());
            provincia.setCodigo(Integer.parseInt(monumento.getCodigo_postal().substring(0, 2)));
            localidad.setNombre(monumento.getLocalidad().toUpperCase());
            localidad.setProvincia(provincia);
            MonumentoDTO monumentoDTO = new MonumentoDTO();
            monumentoDTO.setNombre(monumento.getNombre());
            monumentoDTO.setTipo(monumento.getTipo());
            monumentoDTO.setDireccion(monumento.getDireccion());
            monumentoDTO.setCodigoPostal(monumento.getCodigo_postal());
            monumentoDTO.setLongitud(monumento.getLongitud());
            monumentoDTO.setLatitud(monumento.getLatitud());
            String descripcion = monumento.getDescripcion();
            descripcion = descripcion.substring(0, Math.min(descripcion.length(), 1000));
            monumentoDTO.setDescripcion(descripcion);
            monumentoDTO.setLocalidad(localidad);
            monumentosDTO.add(monumentoDTO);
        }

        return new MonumentosDTO(monumentosDTO, informe);
    }
}



