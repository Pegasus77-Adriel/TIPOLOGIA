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
     * Teste do método adicionaVertice.
     */
    @Test
    public void testAdicionaVertice() {
        System.out.println("adicionaVertice");
        Vertice novoVertice = new Vertice("1", false);
        Grafo grafo = new Grafo();
        boolean resultado = grafo.adicionaVertice(novoVertice);
        assertTrue(resultado);
        resultado = grafo.adicionaVertice(novoVertice);
        assertFalse("Não é permida a adição de vértices com o mesmo nome", resultado);
        novoVertice = new Vertice("2", false);
        resultado = grafo.adicionaVertice(novoVertice);
        assertTrue(resultado);
    }

    /**
     * Teste do método adicionaArestaDupla.
     */
    @Test
    public void testAdicionaArestaDupla() {
        System.out.println("adicionaArestaDupla");
        int peso = 0;
        Vertice origem = null;
        Vertice fim = null;
        Grafo grafo = new Grafo();
        boolean resultado = grafo.adicionaArestaDupla(peso, origem, fim);
        assertFalse("Adição de vértice nulo não é permitida, retorna false", resultado);
        origem = new Vertice("1", true);
        resultado = grafo.adicionaArestaDupla(peso, origem, fim);
        assertFalse(resultado);
        fim = new Vertice("2", false);
        resultado = grafo.adicionaArestaDupla(peso, origem, fim);
        assertTrue(resultado);
    }

    /**
     * Teste do método adicionaArestaSimples.
     */
    @Test
    public void testAdicionaArestaSimples() {
        int peso = 0;
        Vertice origem = null;
        Vertice fim = null;
        Grafo grafo = new Grafo();
        boolean resultado = grafo.adicionaArestaSimples(peso, origem, fim);
        assertFalse("Adição de vértice nulo não é permitida, retorna false", resultado);
        origem = new Vertice("1", true);
        resultado = grafo.adicionaArestaSimples(peso, origem, fim);
        assertFalse(resultado);
        fim = new Vertice("2", false);
        resultado = grafo.adicionaArestaSimples(peso, origem, fim);
        assertTrue(resultado);
    }

    /**
     * Teste do método removeVertice.
     */
    @Test
    public void testRemoveVertice_Vertice() {
        System.out.println("removeVertice");
        Vertice emRemocao = new Vertice("1", true);
        Grafo grafo = new Grafo();
        boolean resultado = grafo.removeVertice(emRemocao);
        assertFalse("Impossível remover um vértice que não foi adicionado", resultado);
        grafo.adicionaVertice(emRemocao);
        resultado = grafo.removeVertice(emRemocao);
        assertTrue(resultado);
        resultado = grafo.removeVertice(emRemocao);
        assertFalse("O vértice já havia sido removido. O retorno é false", resultado);
    }

    /**
     * Teste do método removeVertice.
     */
    @Test
    public void testRemoveVertice_String() {
        System.out.println("removeVertice");
        Vertice emRemocao = new Vertice("1", true);
        Grafo grafo = new Grafo();
        boolean resultado = grafo.removeVertice(emRemocao.getNome());
        assertFalse("Impossível remover um vértice que não foi adicionado", resultado);
        grafo.adicionaVertice(emRemocao);
        resultado = grafo.removeVertice(emRemocao.getNome());
        assertTrue(resultado);
        resultado = grafo.removeVertice(emRemocao.getNome());
        assertFalse("O vértice já havia sido removido. O retorno é false", resultado);
    }

    /**
     * Teste do método estaVazio.
     */
    @Test
    public void testEstaVazio() {
        System.out.println("estaVazio");
        Vertice vertice = new Vertice("1", true);
        Grafo grafo = new Grafo();
        assertTrue("Nada foi adiciona, retorno é true", grafo.estaVazio());
        grafo.adicionaVertice(vertice);
        assertFalse(grafo.estaVazio());
        grafo.removeVertice(vertice);
        assertTrue(grafo.estaVazio());
    }

    /**
     * Teste do método buscaVertice.
     */
    @Test
    public void testBuscaVertice() {
        System.out.println("buscaVertice");
        Vertice novoVertice = new Vertice("2", false);
        String nomeVertice = "1";
        Grafo grafo = new Grafo();
        Vertice buscado = grafo.buscaVertice(nomeVertice);
        assertNull(buscado);
        grafo.adicionaVertice(novoVertice);
        buscado = grafo.buscaVertice(nomeVertice);
        assertNull("Nenhum grafo adicionado possui o nome '1'", buscado);
        nomeVertice = "2";
        buscado = grafo.buscaVertice(nomeVertice);
        assertNotNull(buscado);
        assertEquals(novoVertice, buscado);
    }

    /**
     * Teste do método removeAresta.
     */
    @Test
    public void testRemoveAresta() {
        System.out.println("removeAresta");
        Vertice vertice1 = new Vertice("1", false);
        Vertice vertice2 = new Vertice("2", true);
        String nomeVertice1 = vertice1.getNome();
        String nomeVertice2 = vertice2.getNome();
        Grafo grafo = new Grafo();
        grafo.adicionaVertice(vertice1);
        grafo.adicionaVertice(vertice2);
        boolean resultado = grafo.removeAresta(nomeVertice1, nomeVertice2);
        assertFalse("Nenhuma aresta foi adicionada, por isso será retornado false", resultado);
        grafo.adicionaArestaDupla(0, vertice1, vertice2);
        resultado = grafo.removeAresta(nomeVertice2, nomeVertice1);
        assertTrue(resultado);
        resultado = grafo.removeAresta(nomeVertice2, nomeVertice1);
        assertFalse("A aresta já havia sido removida desses vértices", resultado);
    }

}
