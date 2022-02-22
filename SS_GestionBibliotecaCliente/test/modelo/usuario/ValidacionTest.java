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
public class ValidacionTest {

    public ValidacionTest() {
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
     * Test CEDULA
     */
    @Test
    public void testCedulaLongitud() {
        System.out.println("testCedulaLongitud");
        String value = "1724266091";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaValidada() {
        System.out.println("testCedulaValidada");
        String value = "1724266091";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaCaractereEspeciales() {
        System.out.println("testCedulaCaractereEspeciales");
        String value = "-```+++===";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaLetras() {
        System.out.println("testCedulaLetras");
        String value = "holaholaho";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaBlanco() {
        System.out.println("testCedulaBlanco");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaEspacios() {
        System.out.println("testCedulaEspacios");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaMas10Numeros() {
        System.out.println("testCedulaMas10Numeros");
        String value = "12345678900000";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCedulaMenos10Numeros() {
        System.out.println("testCedulaMenos10Numeros");
        String value = "12345";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }
    
    @Test
    public void testCedulaFormatoIncompleto() {
        System.out.println("testCedulaFormatoIncompleto");
        String value = "1234567890";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.cedula(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test LETRAS
     */
    @Test
    public void testLetras() {
        System.out.println("letras");
        String value = "letras";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.letras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testLetrasNumeros() {
        System.out.println("testLetrasNumeros");
        String value = "12345";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.letras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testLetrasCaracterEspecial() {
        System.out.println("testLetrasCaracterEspecial");
        String value = "````";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.letras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testLetrasEspacios() {
        System.out.println("testLetrasEspacios");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.letras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testLetrasBlanco() {
        System.out.println("testLetrasBlanco");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.letras(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test CORREO
     */
    @Test
    public void testCorreo() {
        System.out.println("correo");
        String value = "mabelenceron@gmail.com";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.correo(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCorreoBlanco() {
        System.out.println("testCorreoBlanco");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.correo(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCorreoCaracterEspecial() {
        System.out.println("testCorreoCaracterEspecial");
        String value = "``````";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.correo(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCorreoEspacios() {
        System.out.println("testCorreoEspacios");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.correo(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testCorreoFormatoIncompleto() {
        System.out.println("testCorreoFormatoIncompleto");
        String value = "mabelenceron@";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.correo(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test TELEFONO
     */
    @Test
    public void testTelefonoLongitud() {
        System.out.println("testTelefonoLongitud");
        String value = "0997702037";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoNumeros() {
        System.out.println("testTelefonoNumeros");
        String value = "29709123";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoCaracterEspecial() {
        System.out.println("testTelefonoCaracterEspecial");
        String value = "````==+";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoLetras() {
        System.out.println("testTelefonoLetras");
        String value = "dasdasdad";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoBlanco() {
        System.out.println("testTelefonoBlanco");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoEspacios() {
        System.out.println("testTelefonoEspacios");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoMas10Numeros() {
        System.out.println("testTelefonoMas10Numeros");
        String value = "123456789011";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testTelefonoMenos7Numeros() {
        System.out.println("testTelefonoMenos7Numeros");
        String value = "12345";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.telefono(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test NUMEROS
     */
    @Test
    public void testNumeros() {
        System.out.println("numeros");
        String value = "12345";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.numeros(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosCaracteresEspeciales() {
        System.out.println("testNumerosCaracteresEspeciales");
        String value = "¡@#$%";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numeros(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosLetras() {
        System.out.println("testNumerosLetras");
        String value = "sasasas";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numeros(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosBlanco() {
        System.out.println("numeros");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numeros(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosEspacios() {
        System.out.println("numeros");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numeros(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosMenoresA0() {
        System.out.println("testNumerosMenoresA0");
        String value = "-4";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numeros(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test HORA
     */
    @Test
    public void testHora() {
        System.out.println("hora");
        String value = "00:10:00";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.hora(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testHoraBlanco() {
        System.out.println("testHoraBlanco");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.hora(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testHoraCaracteresEspeciales() {
        System.out.println("testHoraCaracteresEspeciales");
        String value = "¡@#$%";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.hora(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testHoraEspacios() {
        System.out.println("testHoraEspacios");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.hora(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testHoraLetras() {
        System.out.println("testHoraLetras");
        String value = "sasasa";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.hora(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testHoraFormatoIncompleto() {
        System.out.println("testHoraFormatoIncompleto");
        String value = "00:00";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.hora(value);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of numerosLetras
     */
    @Test
    public void testNumerosLetrasF() {
        System.out.println("testNumerosLetrasF");
        String value = "Usuario123";
        Validacion instance = new Validacion();
        boolean expResult = true;
        boolean result = instance.numerosLetras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosLetrasCaracterEspecial() {
        System.out.println("testNumerosLetrasCaracterEspecial");
        String value = "¡@#$%";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numerosLetras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosLetrasBlanco() {
        System.out.println("testNumerosLetrasBlanco");
        String value = "";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numerosLetras(value);
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNumerosLetrasEspacios() {
        System.out.println("testNumerosLetrasEspacios");
        String value = " ";
        Validacion instance = new Validacion();
        boolean expResult = false;
        boolean result = instance.numerosLetras(value);
        assertEquals(expResult, result);
        
    }

}
