package DAO;

import com.mxgraph.model.mxCell;
import java.util.HashMap;

public class mxCellVerticeDAO {

    private HashMap<String, mxCell> vertices;

    public mxCellVerticeDAO() {
        vertices = new HashMap();
    }

    public mxCell adicionaVerticeI(String nomeVertice, mxCell novo) {
        if (novo == null) {
            return null;
        }
        mxCell adicionado = vertices.put(nomeVertice, novo);
        return adicionado;
    }

    public boolean contemVertice(String nomeVertice) {
        return vertices.containsKey(nomeVertice);
    }

    public mxCell buscaVertice(String nomeVertice) {
        return vertices.get(nomeVertice);
    }

    public boolean estaVazio() {
        return vertices.isEmpty();
    }

    public mxCell removeVerticeI(String nomeVertice) {
        return vertices.remove(nomeVertice);
    }

    public Object[] getVertices() {
        return vertices.values().toArray();
    }
}
