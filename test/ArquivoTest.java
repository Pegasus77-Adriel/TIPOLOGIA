
import Model.Arquivo;
import Model.Grafo;
import Model.Vertice;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     * Teste do método trasfereParaArquivo.
     *
     * durante a execução do teste desse método
     */
    @Test
    public void testTrasfereParaArquivo() {
        System.out.println("trasfereParaArquivo");
        String caminho = "";
        Grafo grafo = new Grafo();
        boolean resultado = false;
        try {
            resultado = Arquivo.trasfereParaArquivo(caminho, grafo);
        }
        catch (IOException | ExceptionInInitializerError ex) {
        }
        finally {
            //De qualquer forma o resultado será false porque o grafo está vazio ou porque não existe caminho especificado:
            assertFalse(resultado);

            assertTrue(grafo.estaVazio());
        }
        caminho = "Arquivo de configuração1.txt"; //Diretório do arquivo (está na pasta do projeto)
        try {
            resultado = Arquivo.trasfereParaArquivo(caminho, grafo);
        }
        catch (IOException | ExceptionInInitializerError ex) {
        }
        finally {
            assertTrue(grafo.estaVazio());
            assertFalse("O caminho está correto mas o grafo continua vazio", resultado);
        }
        grafo.adicionaVertice(new Vertice("Internet", false));
        try {
            resultado = Arquivo.trasfereParaArquivo(caminho, grafo);
            assertTrue("O caminho está correto e o grafo não está vazio", resultado);
        }
        catch (IOException | ExceptionInInitializerError ex) {
            assertFalse("O caminho está correto e o grafo não está vazio mas algum problema aconteceu. Retorno será false", resultado);
        }
        finally {
            assertFalse(grafo.estaVazio());
        }

        Grafo segundoGrafo = new Grafo();
        try {
            Arquivo.trasfereParaGrafo(caminho, segundoGrafo);
            assertFalse("Segundo grafo receberá, indiretamente,"
                    + " as configurações do primeiro grafo. Retorno é true", segundoGrafo.estaVazio());
        }
        catch (IOException ex) {
            assertTrue("O diretório está correto mas aconteceu algum problema"
                    + " durante a leitura do arquivo. Retorno é false", segundoGrafo.estaVazio());
        }
    }

    /**
     * Teste do método trasfereParaGrafo.
     */
    @Test
    public void testTrasfereParaGrafo() {
        System.out.println("trasfereParaGrafo");
        String diretorio = "";
        Grafo grafo = new Grafo();
        try {
            Arquivo.trasfereParaGrafo(diretorio, grafo);
        }
        catch (IOException ex) {
        }
        finally {
            assertTrue(grafo.estaVazio());
        }
        diretorio = "Arquivo de configuração.txt";
        try {
            Arquivo.trasfereParaGrafo(diretorio, grafo);
            assertFalse(grafo.estaVazio());
        }
        catch (IOException ex) {
            assertTrue("O diretório está correto mas aconteceu algum problema"
                    + " durante a leitura do arquivo. Retorno é false", grafo.estaVazio());
        }
    }
}
