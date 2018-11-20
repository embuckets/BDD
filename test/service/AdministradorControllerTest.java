/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Administrador;
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
public class AdministradorControllerTest {
    private AdministradorController administradorControllerTested;
    
    public AdministradorControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        administradorControllerTested = new AdministradorController();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAdministradorByMatricula method, of class AdministradorController.
     */
    @Test
    public void testGetAdministradorByMatricula() {
        System.out.println("getAdministradorByMatricula");
        String matricula = "123456";
        Administrador expResult = new Administrador("123456", "AdminUno", 1);
        Administrador result = administradorControllerTested.getAdministradorByMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAdministradorByMatricula method, of class AdministradorController.
     */
    @Test
    public void testGetAdministradorByMatriculaInvalido() {
        System.out.println("getAdministradorByMatricula");
        String matricula = "12346";
        Administrador expResult = null;
        Administrador result = administradorControllerTested.getAdministradorByMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
