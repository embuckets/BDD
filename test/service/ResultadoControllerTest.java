/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dominio.Encuesta;
import dominio.Opcion;
import dominio.Usuario;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emilio
 */
public class ResultadoControllerTest {
    
    public ResultadoControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getResultadosDeEncuesta method, of class ResultadoController.
     */
    @Test
    public void testGetResultadosDeEncuesta() {
        System.out.println("getResultadosDeEncuesta");
        int idEncuesta = 1;
        int idUnidad = 1;
        ResultadoController instance = new ResultadoController();
//        List<Map.Entry<String, Integer>> expResult = ;
        List<Opcion> result = instance.getResultadosDeEncuesta(idEncuesta, idUnidad);
//        assertEquals(expResult, result);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
    }
    

    /**
     * Test of getResultadosDeEncuesta method, of class ResultadoController.
     */
    @Test
    public void testGetEncuestaConResultados() {
        System.out.println("getResultadosDeEncuesta");
        int idEncuesta = 1;
        int idUnidad = 1;
        Encuesta encuesta = new EncuestaController().getEncuestaById(idEncuesta);
        encuesta.setOpciones(new ResultadoController().getResultadosDeEncuesta(idEncuesta, idUnidad));
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
        try {
            String jsonString = mapper.writeValueAsString(encuesta);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }
    
}
