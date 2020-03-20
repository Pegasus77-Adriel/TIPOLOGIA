
import Model.Vertice;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos e Adriel
 */
public class VerticeTest {

    
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
