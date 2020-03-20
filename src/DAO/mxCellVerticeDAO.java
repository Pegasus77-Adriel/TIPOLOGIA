package DAO;

import com.mxgraph.model.mxCell;
import java.util.HashMap;

/**
 *
 * @author Adryel e Marcos
 */
public class mxCellVerticeDAO {

    private HashMap<String, mxCell> vertices;

    /**
     * Método construtor
     */
    public mxCellVerticeDAO() {
        vertices = new HashMap();
    }

    /**
     * Método de adicionar Vertice
     * @param nomeVertice É nome do vertice
     * @param novo É o novo mxcell
     * @return Retorna mxCell do vértice adicionado
     */
    public mxCell adicionaVerticeI(String nomeVertice, mxCell novo) {
        if (novo == null) {
            return null;
        }
        mxCell adicionado = vertices.put(nomeVertice, novo);
        return adicionado;
    }

    /**
     * Método do vertice se tem ligaçao com vertice
     * @param nomeVertice O nome do vertice a ser verificado
     * @return Retorna o método de verificar se tem ligação
     */
    public boolean contemVertice(String nomeVertice) {
        return vertices.containsKey(nomeVertice);
    }

    /**
     * Método de buscar vertice pelo o nome do vertice
     * @param nomeVertice nome do vertice a ser buscado
     * @return Retorna o mxcell do método de pegar vertice
     */
    public mxCell buscaVertice(String nomeVertice) {
        return vertices.get(nomeVertice);
    }

    /**
     * Método de verificar se está vazio
     * @return Retorna true ou false, caso esteja ou não
     */
    public boolean estaVazio() {
        return vertices.isEmpty();
    }

    /**
     * Método de remover vertice
     * @param nomeVertice o nome do vertice
     * @return Retorna o mxCell do método de remover vertices
     */
    public mxCell removeVerticeI(String nomeVertice) {
        return vertices.remove(nomeVertice);
    }

    /**
     * Método de pegar vertices
     * @return Retorna o método de pegar vertices
     */
    public Object[] getVertices() {
        return vertices.values().toArray();
    }
}
