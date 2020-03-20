package Model;

import com.mxgraph.model.mxCell;
import java.util.HashMap;

/**
 *
 * @author Marcos
 */
public class mxCellArestaDAO {

    HashMap<String, mxCell> arestas;

    public mxCellArestaDAO() {
        arestas = new HashMap();
    }

    public mxCell buscaAresta(String nomeVertice1, String nomeVertice2) {
        mxCell buscada = arestas.get(nomeVertice1 + "\n" + nomeVertice2);
        return buscada == null ? arestas.get(nomeVertice2 + "\n" + nomeVertice1) : buscada;
    }

    public void adicionaArestaI(String nomeVertice1, String nomeVertice2, mxCell novaAresta) {
        if (this.buscaAresta(nomeVertice1, nomeVertice2) == null) {
            arestas.put(nomeVertice1 + "\n" + nomeVertice2, novaAresta);
        }
    }

    public mxCell removeArestaI(mxCell emRemocao) {
        return this.removeArestaI((String) emRemocao.getValue());
    }

    public mxCell removeArestaI(String identificadorAresta) {
        for (Object keyAtual : arestas.keySet()) {
            if (keyAtual.equals(identificadorAresta)) {
                return arestas.remove(identificadorAresta);
            }
        }
        return null;
    }
}
