/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Encuesta;
import java.io.IOException;
import java.time.LocalDateTime;
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
public class EncuestasControllerTest {

    private EncuestaController encuestasControllerTested;

    public EncuestasControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        encuestasControllerTested = new EncuestaController();
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
        List<Encuesta> expResult = null;
        List<Encuesta> result = encuestasControllerTested.getEncuestasByIdUnidad(idUnidad);
        result.stream().forEach((e) -> System.out.println(e));
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getEncuestasByIdUnidad method, of class EncuestaController.
     */
    @Test
    public void testArrayEncuestasToJson() {
        System.out.println("getEncuestasByIdUnidad");
        List<Encuesta> encuestas = new ArrayList<>();
        encuestas.add(new Encuesta(1, "titulo1", "descripcion1", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1));
        encuestas.add(new Encuesta(2, "titulo2", "descripcion2", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1));
        encuestas.add(new Encuesta(3, "titulo3", "descripcion3", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 2));
        encuestas.add(new Encuesta(4, "titulo4", "descripcion4", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 2));

        ObjectMapper mapper = new ObjectMapper();
        try {
            String result = mapper.writeValueAsString(encuestas);
            System.out.println(result);

            List<Encuesta> asList = mapper.readValue(result, new TypeReference<List<Encuesta>>() {
            });
            asList.forEach((e) -> System.out.println(e));
//        assertEquals(expResult, result);
// TODO review the generated test code and remove the default call to fail.
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
