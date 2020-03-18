package View;

import com.mxgraph.view.mxGraph;

public class IRooteador extends IDispositivos {

    public IRooteador(mxGraph grafo) {
        super(grafo);
    }

    @Override
    public String caminhoImagem() {
        return "file:rooteador.gif";
    }

    @Override
    public String nomeEstilo() {
        return "rooteador";
    }

}
