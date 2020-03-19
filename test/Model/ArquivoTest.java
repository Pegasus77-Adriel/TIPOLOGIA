/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos
 */
public class ArquivoTest {
    
    public ArquivoTest() {
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
     * Test of trasfereParaArquivo method, of class Arquivo.
     */
    @Test
    public void testTrasfereParaArquivo() throws Exception {
        System.out.println("trasfereParaArquivo");
        String caminho = "";
        Grafo grafo = null;
        boolean expResult = false;
        boolean result = Arquivo.trasfereParaArquivo(caminho, grafo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of trasfereParaGrafo method, of class Arquivo.
     */
    @Test
    public void testTrasfereParaGrafo() throws Exception {
        System.out.println("trasfereParaGrafo");
        String diretorio = "";
        Grafo grafo = null;
        Arquivo.trasfereParaGrafo(diretorio, grafo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
