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
     * Test of getVotado method, of class Encuesta.
     */
    @Test
    public void testGetVotado() {
        System.out.println("getVotado");
        Encuesta instance = new Encuesta();
        Opcion expResult = null;
        Opcion result = instance.getVotado();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVotado method, of class Encuesta.
     */
    @Test
    public void testSetVotado() {
        System.out.println("setVotado");
        Opcion votado = null;
        Encuesta instance = new Encuesta();
        instance.setVotado(votado);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdEncuesta method, of class Encuesta.
     */
    @Test
    public void testGetIdEncuesta() {
        System.out.println("getIdEncuesta");
        Encuesta instance = new Encuesta();
        int expResult = 0;
        int result = instance.getIdEncuesta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdEncuesta method, of class Encuesta.
     */
    @Test
    public void testSetIdEncuesta() {
        System.out.println("setIdEncuesta");
        int idEncuesta = 0;
        Encuesta instance = new Encuesta();
        instance.setIdEncuesta(idEncuesta);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitulo method, of class Encuesta.
     */
    @Test
    public void testGetTitulo() {
        System.out.println("getTitulo");
        Encuesta instance = new Encuesta();
        String expResult = "";
        String result = instance.getTitulo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitulo method, of class Encuesta.
     */
    @Test
    public void testSetTitulo() {
        System.out.println("setTitulo");
        String titulo = "";
        Encuesta instance = new Encuesta();
        instance.setTitulo(titulo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescripcion method, of class Encuesta.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Encuesta instance = new Encuesta();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescripcion method, of class Encuesta.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String descripcion = "";
        Encuesta instance = new Encuesta();
        instance.setDescripcion(descripcion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAbre method, of class Encuesta.
     */
    @Test
    public void testGetAbre() {
        System.out.println("getAbre");
        Encuesta instance = new Encuesta();
        LocalDateTime expResult = null;
        LocalDateTime result = instance.getAbre();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAbre method, of class Encuesta.
     */
    @Test
    public void testSetAbre() {
        System.out.println("setAbre");
        LocalDateTime abre = null;
        Encuesta instance = new Encuesta();
        instance.setAbre(abre);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCierra method, of class Encuesta.
     */
    @Test
    public void testGetCierra() {
        System.out.println("getCierra");
        Encuesta instance = new Encuesta();
        LocalDateTime expResult = null;
        LocalDateTime result = instance.getCierra();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCierra method, of class Encuesta.
     */
    @Test
    public void testSetCierra() {
        System.out.println("setCierra");
        LocalDateTime cierra = null;
        Encuesta instance = new Encuesta();
        instance.setCierra(cierra);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdUnidad method, of class Encuesta.
     */
    @Test
    public void testGetIdUnidad() {
        System.out.println("getIdUnidad");
        Encuesta instance = new Encuesta();
        int expResult = 0;
        int result = instance.getIdUnidad();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUnidad method, of class Encuesta.
     */
    @Test
    public void testSetIdUnidad() {
        System.out.println("setIdUnidad");
        int idUnidad = 0;
        Encuesta instance = new Encuesta();
        instance.setIdUnidad(idUnidad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpciones method, of class Encuesta.
     */
    @Test
    public void testGetOpciones() {
        System.out.println("getOpciones");
        Encuesta instance = new Encuesta();
        List<Opcion> expResult = null;
        List<Opcion> result = instance.getOpciones();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOpcion method, of class Encuesta.
     */
    @Test
    public void testAddOpcion() {
        System.out.println("addOpcion");
        Opcion opcion = null;
        Encuesta instance = new Encuesta();
        instance.addOpcion(opcion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOpciones method, of class Encuesta.
     */
    @Test
    public void testSetOpciones() {
        System.out.println("setOpciones");
        List<Opcion> opciones = null;
        Encuesta instance = new Encuesta();
        instance.setOpciones(opciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearOpciones method, of class Encuesta.
     */
    @Test
    public void testCrearOpciones() {
        System.out.println("crearOpciones");
        String[] opciones = null;
        Encuesta instance = new Encuesta();
        instance.crearOpciones(opciones);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Encuesta.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Encuesta instance = new Encuesta();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Encuesta.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Encuesta instance = new Encuesta();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Encuesta.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Encuesta instance = new Encuesta();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
