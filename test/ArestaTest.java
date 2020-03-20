
import Model.Aresta;
import Model.Vertice;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos e Adriel
 */
public class ArestaTest {

    /**
     * Teste de retornar peso da Aresta.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Vertice vertice = new Vertice("A", true);
        Aresta aresta = new Aresta(3, vertice);
        int esperado = 3;
        int resultado = aresta.getPeso();
        assertEquals(esperado, resultado);
    }

    /**
     * Teste de retornar Vertice final da aresta.
     */
    @Test
    public void testGetFim() {
        System.out.println("getFim");
        Vertice vertice = new Vertice("A", true);
        Aresta aresta = new Aresta(3, vertice);
        Vertice esperado = vertice;
        Vertice resultado = aresta.getFim();
        assertEquals(esperado, resultado);
    }

}
