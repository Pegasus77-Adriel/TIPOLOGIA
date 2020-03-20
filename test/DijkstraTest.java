
import Model.Dijkstra;
import Model.Grafo;
import Model.Vertice;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos
 */
public class DijkstraTest {

    @Test
    public void testObtemMenoresCaminhos() {
     
        Vertice v1 = new Vertice("A",true); // instanciando Vertices
        Vertice v2 = new Vertice("B",true);
        Vertice v3 = new Vertice("C",true);
     
        Grafo grafo = new Grafo(); // instanciando Grafo
        
        grafo.adicionaVertice(v1); // adicionando Vertice ao Grafo
        grafo.adicionaVertice(v2);
        grafo.adicionaVertice(v3);
        
        grafo.adicionaArestaSimples(5, v1, v2); // adicionando aresta ao Graafo
        grafo.adicionaArestaSimples(10, v1, v3);
        grafo.adicionaArestaSimples(3, v2, v3);
        
        // v1-5-v2
        // v1-10-v3  ILUSTRAÇÃO
        // v2-3-v3
        
        Dijkstra dijkstra = new Dijkstra();
        LinkedList esperado = new LinkedList();
        esperado.add(v1); // adicionando vertices em ordem, a qual será a  
        esperado.add(v2); // sequência do menor caminho
        esperado.add(v3);
        
        
        LinkedList<Vertice> resultado = dijkstra.obtemMenoresCaminhos(v1,grafo.getVertices());
        assertEquals(esperado, resultado); // comparando se 
        // a sequencia retornada dos vertices , é a sequência de menor caminho.
        // Que nesse caso será através de um nó intermediário

        esperado.clear(); 
        
        esperado.add(v1); // mudando a sequência 
        esperado.add(v3);
        esperado.add(v2);
        
        assertNotEquals(esperado,resultado); // comparando se sequência retornada
        // dos vertices, NÃO é a sequência de menor caminho 
        
    }

}
