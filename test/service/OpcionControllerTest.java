/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Opcion;
import java.util.ArrayList;
import java.util.List;
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
public class OpcionControllerTest {

    private OpcionController opcionControllerTested;
    private Opcion opcionParoSi;
    private Opcion opcionParoNo;

    public OpcionControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        opcionControllerTested = new OpcionController();
        opcionParoSi = new Opcion(1, 1, "si");
        opcionParoNo = new Opcion(2, 1, "no");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getOpcionesByIdEncuesta method, of class OpcionController.
     */
    @Test
    public void testGetOpcionesByIdEncuesta() {
        System.out.println("testGetOpcionesByIdEncuesta");
        int idEncuesta = 1;
        List<Opcion> expResult = new ArrayList<>();
        expResult.add(opcionParoSi);
        expResult.add(opcionParoNo);
        List<Opcion> result = opcionControllerTested.getOpcionesByIdEncuesta(idEncuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getOpcionesByIdEncuesta method, of class OpcionController.
     */
    @Test
    public void testGetOpcionesByIdEncuestaJSON() {
        System.out.println("testGetOpcionesByIdEncuesta");
        int idEncuesta = 1;
        List<Opcion> expResult = new ArrayList<>();
        expResult.add(opcionParoSi);
        expResult.add(opcionParoNo);
        List<Opcion> result = opcionControllerTested.getOpcionesByIdEncuesta(idEncuesta);
        assertEquals(expResult, result);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonString = mapper.writeValueAsString(result);
            System.out.println(jsonString);
//        result.stream().forEach((e) -> System.out.println(e));
// TODO review the generated test code and remove the default call to fail.
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        // TODO review the generated test code and remove the default call to fail.
    }

}
