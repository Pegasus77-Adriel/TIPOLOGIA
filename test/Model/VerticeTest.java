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
     * Teste do método adicionaAresta.
     */
    @Test
    public void testAddAresta() {
        System.out.println("addAresta");
        Vertice destino = null;
        int peso = 0;
        Vertice origem = new Vertice("1", false);
        boolean resultado = origem.adicionaAresta(destino, peso);
        assertFalse("É impossível adicionar algo nulo. Retorno será false", resultado);
        destino = new Vertice("2", false);
        resultado = origem.adicionaAresta(destino, peso);
        assertTrue(resultado);
        resultado = origem.adicionaAresta(destino, peso);
        assertTrue("Pode haver mais de uma aresta entre dois vértices. Retorno será true", resultado);
    }

    /**
     * Teste do método getPesoLigacaoCom.
     */
    @Test
    public void testGetPesoLigacaoCom() {
        System.out.println("getPesoLigacaoCom");
        int pesoLigacao = 0;
        Vertice adjacente = new Vertice("2", true);
        Vertice vertice = new Vertice("1", false);
        int resultado = vertice.getPesoLigacaoCom(adjacente);
        assertSame(-1, resultado);
        vertice.adicionaAresta(adjacente, pesoLigacao);
        resultado = vertice.getPesoLigacaoCom(adjacente);
        assertNotSame(-1, resultado);
        assertSame(pesoLigacao, resultado);
    }

    /**
     * Teste do método removeLigacaoCom.
     */
    @Test
    public void testRemoveLigacaoCom() {
        System.out.println("removeLigacaoCom");
        Vertice emRemocao = new Vertice("2", false);
        Vertice vertice = new Vertice("1", false);
        boolean resultado = vertice.removeLigacaoCom(emRemocao);
        assertFalse("A ligação ainda não foi estabelecida. O retorno será false", resultado);
        vertice.adicionaAresta(emRemocao, 0);
        resultado = vertice.removeLigacaoCom(emRemocao);
        assertTrue(resultado);
        resultado = vertice.removeLigacaoCom(emRemocao);
        assertFalse("Não existe mais nenhuma ligação. O retorno será false", resultado);
    }

}
