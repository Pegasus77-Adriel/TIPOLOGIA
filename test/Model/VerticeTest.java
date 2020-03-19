/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;
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
public class VerticeTest {
    
    public VerticeTest() {
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
     * Test of addAresta method, of class Vertice.
     */
    @Test
    public void testAddAresta() {
        System.out.println("addAresta");
        Vertice destino = null;
        int peso = 0;
        Vertice instance = null;
        boolean expResult = false;
        boolean result = instance.addAresta(destino, peso);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArestas method, of class Vertice.
     */
    @Test
    public void testGetArestas() {
        System.out.println("getArestas");
        Vertice instance = null;
        LinkedList<Aresta> expResult = null;
        LinkedList<Aresta> result = instance.getArestas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPesoLigacaoCom method, of class Vertice.
     */
    @Test
    public void testGetPesoLigacaoCom() {
        System.out.println("getPesoLigacaoCom");
        Vertice adjacente = null;
        Vertice instance = null;
        int expResult = 0;
        int result = instance.getPesoLigacaoCom(adjacente);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNome method, of class Vertice.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Vertice instance = null;
        String expResult = "";
        String result = instance.getNome();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDistanciaOrigem method, of class Vertice.
     */
    @Test
    public void testSetDistanciaOrigem() {
        System.out.println("setDistanciaOrigem");
        int novaDistanciaOrigem = 0;
        Vertice instance = null;
        instance.setDistanciaOrigem(novaDistanciaOrigem);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistanciaOrigem method, of class Vertice.
     */
    @Test
    public void testGetDistanciaOrigem() {
        System.out.println("getDistanciaOrigem");
        Vertice instance = null;
        int expResult = 0;
        int result = instance.getDistanciaOrigem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVerticeAntecessor method, of class Vertice.
     */
    @Test
    public void testSetVerticeAntecessor() {
        System.out.println("setVerticeAntecessor");
        Vertice novoAntecessor = null;
        Vertice instance = null;
        instance.setVerticeAntecessor(novoAntecessor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVerticeAntecessor method, of class Vertice.
     */
    @Test
    public void testGetVerticeAntecessor() {
        System.out.println("getVerticeAntecessor");
        Vertice instance = null;
        Vertice expResult = null;
        Vertice result = instance.getVerticeAntecessor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTerminal method, of class Vertice.
     */
    @Test
    public void testIsTerminal() {
        System.out.println("isTerminal");
        Vertice instance = null;
        boolean expResult = false;
        boolean result = instance.isTerminal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLigacaoCom method, of class Vertice.
     */
    @Test
    public void testRemoveLigacaoCom() {
        System.out.println("removeLigacaoCom");
        Vertice emRemocao = null;
        Vertice instance = null;
        boolean expResult = false;
        boolean result = instance.removeLigacaoCom(emRemocao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
