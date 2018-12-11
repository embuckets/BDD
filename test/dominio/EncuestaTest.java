/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.time.LocalDateTime;
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
public class EncuestaTest {
    
    public EncuestaTest() {
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
     * Test of generateID method, of class Encuesta.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        Encuesta instance = new Encuesta();
        for (int i = 0 ; i < 10 ; i++){
            System.out.println(instance.generateID());
        }
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
