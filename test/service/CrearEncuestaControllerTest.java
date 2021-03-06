/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Encuesta;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yaqic
 */
public class CrearEncuestaControllerTest {
    private Encuesta encuestaTested;
    private CrearEncuestaController encuestaController;
    
    
    public CrearEncuestaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        encuestaTested = new Encuesta(0, "testazc", "testazc", LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1);
        String [] opciones = {"unotestazc", "dostestazc"};
        encuestaTested.crearOpciones(opciones);
        encuestaController = new CrearEncuestaController();
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of crearEncuesta method, of class CrearEncuestaController.
     */
    @Test
    public void testCrearEncuesta() {
        System.out.println("crearEncuesta");
        boolean expResult = true;
        boolean result = encuestaController.crearEncuesta(encuestaTested, encuestaTested.getIdUnidad());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isIDEncuestaValid method, of class CrearEncuestaController.
     */
    @Test
    public void testIsIDValid() {
        System.out.println("isIDValid");
        int idEncuesta = 0;
        int idUnidad = 0;
        CrearEncuestaController instance = new CrearEncuestaController();
        boolean expResult = false;
        for (int i = 0; i<1 ; i++){
            int rand= encuestaTested.generateID();
            System.out.println(5 + " : " + instance.isIDEncuestaValid(5, 1)); 
            
        }
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
