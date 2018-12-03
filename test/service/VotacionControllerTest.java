/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Opcion;
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
public class VotacionControllerTest {

    VotacionController votacionControllerTested;

    public VotacionControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        votacionControllerTested = new VotacionController();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of saveVotacion method, of class VotacionController.
     */
    @Test
    public void testSaveVotacion() {
        System.out.println("saveVotacion");
        int idEncuesta = 1;
        int idOpcion = 1;
        String matricula = "2143032439";
        boolean result = votacionControllerTested.saveVotacion(idEncuesta, idOpcion, matricula);
        assertEquals(true, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of updateVotacion method, of class VotacionController.
     */
    @Test
    public void testUpdateVotacion() {
        System.out.println("updateVotacion");
        int idEncuesta = 1;
        int idOpcion = 2;
        String matricula = "2143032439";
        boolean result = votacionControllerTested.updateVotacion(idEncuesta, idOpcion, matricula);
        assertEquals(true, result);
    }

    /**
     * Test of getVotacion method, of class VotacionController.
     */
    @Test
    public void testHaVotado() {
        System.out.println("haVotado");
        int idEncuesta = 1;
        String matricula = "2143032439";
        boolean expResult = true;
        Opcion result = votacionControllerTested.getOpcion(idEncuesta, matricula);
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getVotacion method, of class VotacionController.
     */
    @Test
    public void testHaVotadoFalso() {
        System.out.println("haVotado");
        int idEncuesta = 2;
        String matricula = "2143032439";
        boolean expResult = false;
        Opcion result = votacionControllerTested.getOpcion(idEncuesta, matricula);
        assertNull(result);
        // TODO review the generated test code and remove the default call to fail.
    }

}
