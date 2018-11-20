/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
public class LogInControllerTest {
    
    public LogInControllerTest() {
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
     * Test of logIn method, of class LogInController.
     */
    @Test
    public void testLogInValido() {
        System.out.println("logIn");
        String user = "2143032439";
        String password = "2143032439";
        LogInController instance = new LogInController();
        String expResult = "alumno";
        String result = instance.logIn(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    /**
     * Test of logIn method, of class LogInController.
     */
    @Test
    public void testLogInInvalido() {
        System.out.println("logIn");
        String user = "214303243";
        String password = "2143032439";
        LogInController instance = new LogInController();
        String expResult = null;
        String result = instance.logIn(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
