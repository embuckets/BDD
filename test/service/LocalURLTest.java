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
public class LocalURLTest {
    private JdbcUrl localURLTested;
    
    public LocalURLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        localURLTested = new JdbcUrl("database-local.xml");
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getURL method, of class JdbcUrl.
     */
    @Test
    public void testGetURL() {
        System.out.println("getURL");
        assertEquals("jdbc:mysql://localhost:3306/sivo?user=emilio&password=emilio", localURLTested.getURL());
    }

    /**
     * Test of init method, of class JdbcUrl.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        localURLTested = new JdbcUrl("database-local.xml");
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
