
import Model.Dijkstra;
import Model.Grafo;
import Model.Vertice;
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
public class DijkstraTest {

    public DijkstraTest() {
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
     * Test of obtemMenoresCaminhos method, of class Dijkstra.
     */
    @Test
    public void testObtemMenoresCaminhos() {
        System.out.println("obtemMenoresCaminhos");
        Vertice pontoPartida = null;
        Grafo conjuntoVertices = null;
        Dijkstra instance = new Dijkstra();
        Grafo expResult = null;
        Grafo result = instance.obtemMenoresCaminhos(pontoPartida, conjuntoVertices);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
