/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author emilio
 */
public class PartitionRules {
    public static String DB_LOCAL = "database-local.xml";
    public static String DB_NUBE = "database-nube.xml";
    public static String DEFAULT_ID = "1";
    
    private Map<String, String> map;

    public PartitionRules() {
        this.map = new HashMap<>();
        init();
    }

    private void init() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource("resources/partitions.json");
        File file = new File(url.getFile());
        try (FileReader reader = new FileReader(file)) {
            char[] contents = new char[(int) file.length()];
            reader.read(contents, 0, contents.length);
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(String.valueOf(contents), Map.class);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Map getMap() {
        return map;
    }

    private List<String> getPropertiesFiles(String tabla, String idUnidad) {
        ObjectMapper mapper = new ObjectMapper();
        List<String> result = new ArrayList<>();
        try {
            String json = mapper.writeValueAsString(map);
            JsonNode root = mapper.readTree(json);
            JsonNode particiones = root.get("particiones");
            Iterator<JsonNode> particionesElements = particiones.iterator();
            while (particionesElements.hasNext()) {
                JsonNode tablaElement = particionesElements.next();
                if (tabla.equalsIgnoreCase(tablaElement.get("tabla").asText())) {
                    JsonNode fragmentos = tablaElement.get("fragmentos");
                    Iterator<JsonNode> fragmentosElements = fragmentos.iterator();
                    while (fragmentosElements.hasNext()) {
                        JsonNode fragmentoElement = fragmentosElements.next();
                        if (idUnidad.equalsIgnoreCase(fragmentoElement.get("id_unidad").asText())) {
                            JsonNode urls = fragmentoElement.get("url");
                            Iterator<JsonNode> urlsElements = urls.iterator();
                            while (urlsElements.hasNext()) {
                                JsonNode urlElement = urlsElements.next();
                                result.add(urlElement.asText());
                            }
                            return result;
                        }
                    }
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<String> getUrls(String tabla, String idUnidad) {
        List<String> propertiesFiles = getPropertiesFiles(tabla, idUnidad);
        List<String> result = new ArrayList<>();
        for (String archivo : propertiesFiles) {
            JdbcUrl jdbcUrl = new JdbcUrl(archivo);
            result.add(jdbcUrl.getURL());
        }
        return result;
    }
}
