package View;

import com.mxgraph.view.mxGraph;

/**
 *
 * @author Marcos
 */
public class IRooteador extends IDispositivos {

    /**
     *
     * @param grafo
     */
    public IRooteador(mxGraph grafo) {
        super(grafo);
    }

    /**
     *
     * @return
     */
    @Override
    public String caminhoImagem() {
        return "file:rooteador.gif";
    }

    /**
     *
     * @return
     */
    @Override
    public String nomeEstilo() {
        return "rooteador";
    }

}
