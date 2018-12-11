/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import dominio.Alumno;
import dominio.Encuesta;
import dominio.Usuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
public class EncuestaControllerTest {

    private EncuestaController encuestaControllerTested;
    private Encuesta encuestaAzc;

    public EncuestaControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        encuestaControllerTested = new EncuestaController();
        encuestaAzc = new Encuesta(1, "Paro", "Por falta de agua", LocalDateTime.parse("2018-11-19T12:50:49"), LocalDateTime.parse("2018-11-20T12:50:49"), 1);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEncuestasByIdUnidad method, of class EncuestaController.
     */
    @Test
    public void testGetEncuestasByIdUnidad() {
        System.out.println("getEncuestasByIdUnidad");
        int idUnidad = 1;
        EncuestaController instance = new EncuestaController();
        List<Encuesta> expResult = new ArrayList<>();
        expResult.add(encuestaAzc);
        LocalDate abre = LocalDate.now().minusDays(7);
        LocalDate cierra = LocalDate.now().plusDays(7);
        List<Encuesta> result = instance.getEncuestasByIdUnidad(idUnidad, abre.format(DateTimeFormatter.ISO_LOCAL_DATE), cierra.format(DateTimeFormatter.ISO_LOCAL_DATE));
        assertEquals(expResult, result);
//        result.stream().forEach((e) -> System.out.println(e));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getEncuestasByIdUnidad method, of class EncuestaController.
     */
    @Test
    public void testGetEncuestasByIdUnidadJSON() {
        System.out.println("getEncuestasByIdUnidadJSON");
        int idUnidad = 1;
        EncuestaController instance = new EncuestaController();
        List<Encuesta> expResult = new ArrayList<>();
        expResult.add(encuestaAzc);
        LocalDate abre = LocalDate.now().minusDays(7);
        LocalDate cierra = LocalDate.now().plusDays(7);
        List<Encuesta> result = instance.getEncuestasByIdUnidad(idUnidad, abre.format(DateTimeFormatter.ISO_LOCAL_DATE), cierra.format(DateTimeFormatter.ISO_LOCAL_DATE));
//        assertEquals(expResult, result);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        try {
            String jsonString = mapper.writeValueAsString(result);
            System.out.println(jsonString);
            mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String otro = mapper.writeValueAsString(result);
            System.out.println(otro);

//        result.stream().forEach((e) -> System.out.println(e));
// TODO review the generated test code and remove the default call to fail.
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of getEncuestaById method, of class EncuestaController.
     */
    @Test
    public void testGetEncuestasById() {
        System.out.println("getEncuestasById");
        int idEncuesta = 1;
        EncuestaController instance = new EncuestaController();
        Encuesta expResult = encuestaAzc;
        Encuesta result = instance.getEncuestaById(idEncuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getEncuestaById method, of class EncuestaController.
     */
    @Test
    public void testGetEncuestasYVoto() {
        System.out.println("getEncuestasById");
        Alumno usuario = new Alumno();
        usuario.setIdUnidad(2);
        usuario.setMatricula("2143032439");
        int idUnidad = 1;
        EncuestaController encuestaController = new EncuestaController();
        LocalDate abre = LocalDate.now().minusDays(7);
        LocalDate cierra = LocalDate.now().plusDays(7);
        List<Encuesta> encuestas = encuestaController.getEncuestasByIdUnidad(idUnidad, abre.format(DateTimeFormatter.ISO_LOCAL_DATE), cierra.format(DateTimeFormatter.ISO_LOCAL_DATE));
        VotacionController votacionController = new VotacionController();
        for (Encuesta encuesta : encuestas) {
            encuesta.setVotado(votacionController.getOpcionVotada(encuesta.getIdEncuesta(), usuario.getMatricula(), usuario.getIdUnidad()));
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);
        try {
            String jsonString = mapper.writeValueAsString(encuestas);
            System.out.println(jsonString);
            // TODO review the generated test code and remove the default call to fail.
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
    }

}
