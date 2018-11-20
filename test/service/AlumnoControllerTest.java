/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dominio.Alumno;
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
public class AlumnoControllerTest {
    private AlumnoController alumnoControllerTested;
    
    public AlumnoControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        alumnoControllerTested = new AlumnoController();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAlumnoByMatricula method, of class AlumnoController.
     */
    @Test
    public void testGetAlumnoByMatriculaValido() {
        System.out.println("getAlumnoByMatricula");
        String matricula = "2143032439";
        AlumnoController instance = new AlumnoController();
        Alumno expResult = new Alumno("2143032439", "Emilio", "Hernandez", "Segovia", 1, 1);
        Alumno result = instance.getAlumnoByMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of getAlumnoByMatricula method, of class AlumnoController.
     */
    @Test
    public void testGetAlumnoByMatriculaInvalido() {
        System.out.println("getAlumnoByMatricula");
        String matricula = "21430324";
        AlumnoController instance = new AlumnoController();
        Alumno expResult = null;
        Alumno result = instance.getAlumnoByMatricula(matricula);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
