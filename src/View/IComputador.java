package View;

import com.mxgraph.view.mxGraph;

public class IComputador extends IDispositivos {
    
    public IComputador(mxGraph grafo) {
        super(grafo);
    }

    @Override
    public String caminhoImagem() {
        return "file:computador.gif";
    }

    @Override
    public String nomeEstilo() {
        return "computador";
    }
    
}
