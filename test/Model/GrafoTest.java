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
public class GrafoTest {
    
    public GrafoTest() {
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
     * Test of adicionaVertice method, of class Grafo.
     */
    @Test
    public void testAdicionaVertice() {
        System.out.println("adicionaVertice");
        Vertice novoVertice = null;
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.adicionaVertice(novoVertice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionaArestaDupla method, of class Grafo.
     */
    @Test
    public void testAdicionaArestaDupla() {
        System.out.println("adicionaArestaDupla");
        int peso = 0;
        Vertice origem = null;
        Vertice fim = null;
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.adicionaArestaDupla(peso, origem, fim);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adicionaArestaSimples method, of class Grafo.
     */
    @Test
    public void testAdicionaArestaSimples() {
        System.out.println("adicionaArestaSimples");
        int peso = 0;
        Vertice origem = null;
        Vertice fim = null;
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.adicionaArestaSimples(peso, origem, fim);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVertices method, of class Grafo.
     */
    @Test
    public void testGetVertices() {
        System.out.println("getVertices");
        Grafo instance = new Grafo();
        LinkedList<Vertice> expResult = null;
        LinkedList<Vertice> result = instance.getVertices();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVertice method, of class Grafo.
     */
    @Test
    public void testRemoveVertice_Vertice() {
        System.out.println("removeVertice");
        Vertice emRemocao = null;
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.removeVertice(emRemocao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVertice method, of class Grafo.
     */
    @Test
    public void testRemoveVertice_String() {
        System.out.println("removeVertice");
        String nomeVertice = "";
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.removeVertice(nomeVertice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estaVazio method, of class Grafo.
     */
    @Test
    public void testEstaVazio() {
        System.out.println("estaVazio");
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.estaVazio();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscaVertice method, of class Grafo.
     */
    @Test
    public void testBuscaVertice() {
        System.out.println("buscaVertice");
        String nomeVertice = "";
        Grafo instance = new Grafo();
        Vertice expResult = null;
        Vertice result = instance.buscaVertice(nomeVertice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAresta method, of class Grafo.
     */
    @Test
    public void testRemoveAresta() {
        System.out.println("removeAresta");
        String nomeVertice1 = "";
        String nomeVertice2 = "";
        Grafo instance = new Grafo();
        boolean expResult = false;
        boolean result = instance.removeAresta(nomeVertice1, nomeVertice2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
