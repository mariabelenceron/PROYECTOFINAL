/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.usuario;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mabel
 */
public class EncryptTest {
    
    public EncryptTest() {
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
     * Test of getAES method, of class Encrypt.
     */
    @Test
    public void testGetAES() {
        System.out.println("getAESNormal");
        String data = "admin";
        Encrypt instance = new Encrypt();
        String expResult = "iV6QgS0h2v+mmbRtigCCAA==";
        String result = instance.getAES(data);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAESCaracteres() {
        System.out.println("getAESCaracteres");
        String data = "=-=-";
        Encrypt instance = new Encrypt();
        String expResult = "Error";
        String result = instance.getAES(data);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAESBlanco() {
        System.out.println("getAESBlanco");
        String data = "";
        Encrypt instance = new Encrypt();
        String expResult = "Error";
        String result = instance.getAES(data);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of getAESDecrypt method, of class Encrypt.
     */
    @Test
    public void testGetAESDecrypt() {
        System.out.println("getAESDecryptNormal");
        String data = "iV6QgS0h2v+mmbRtigCCAA==";
        Encrypt instance = new Encrypt();
        String expResult = "admin";
        String result = instance.getAESDecrypt(data);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAESDecryptBlanco() {
        System.out.println("getAESDecryptBlanco");
        String data = "";
        Encrypt instance = new Encrypt();
        String expResult = "Error";
        String result = instance.getAESDecrypt(data);
        assertEquals(expResult, result);
    }
}
