/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
public class PartitionRulesTest {
    
    public PartitionRulesTest() {
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
     * Test of getMap method, of class PartitionRules.
     */
    @Test
    public void testGetMap() {
        System.out.println("getMap");
        PartitionRules instance = new PartitionRules();
        Map expResult = null;
        Map result = instance.getMap();
        System.out.println(result.toString());
        
        assertTrue(!result.isEmpty());
        
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPropertiesFiles method, of class PartitionRules.
     */
    @Test
    public void testGetURL() {
//        System.out.println("getURL");
//        String tabla = "alumno";
//        String idUnidad = "1";
//        PartitionRules instance = new PartitionRules();
//        List<String> expResult = null;
//        List<String> result = instance.getPropertiesFiles(tabla, idUnidad);
//        System.out.println(result);
//        assertTrue(!result.isEmpty());
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPropertiesFiles method, of class PartitionRules.
     */
    @Test
    public void testGetPropertiesFiles() {
//        System.out.println("getPropertiesFiles");
//        String tabla = "";
//        String idUnidad = "";
//        PartitionRules instance = new PartitionRules();
//        List<String> expResult = null;
//        List<String> result = instance.getPropertiesFiles(tabla, idUnidad);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getUrls method, of class PartitionRules.
     */
    @Test
    public void testGetUrlsAzc() {
        System.out.println("getUrls");
        String[] urls = {"jdbc:mysql://localhost:3306/sivo?user=emilio&password=emilio", "jdbc:mysql://test.ca0vmrsmxt1q.sa-east-1.rds.amazonaws.com:3306/test?user=embuckets&password=evangelion01"};
        PartitionRules instance = new PartitionRules();
        List<String> expResult = Arrays.asList(urls);
        String tabla = "alumno";
        String idUnidad = "1";
        List<String> result = instance.getUrls(tabla, idUnidad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
    /**
     * Test of getUrls method, of class PartitionRules.
     */
    @Test
    public void testGetUrlsCuaji() {
        System.out.println("getUrls");
        String[] urls = {"jdbc:mysql://test.ca0vmrsmxt1q.sa-east-1.rds.amazonaws.com:3306/test?user=embuckets&password=evangelion01"};
        PartitionRules instance = new PartitionRules();
        List<String> expResult = Arrays.asList(urls);
        String tabla = "alumno";
        String idUnidad = "2";
        List<String> result = instance.getUrls(tabla, idUnidad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
