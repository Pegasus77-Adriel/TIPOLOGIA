package View;

import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;

public class IRemoveVertice {

    public IRemoveVertice(mxGraph painel, mxICell vertice) {
        painel.getModel().remove(vertice);
        for (int i = 0; i < vertice.getEdgeCount() + 1; i++) {
            painel.getModel().remove(vertice.getEdgeAt(0)); //Removendo sempre a primeira aresta do vértice
        }
    }

}
