package DAO;

import com.mxgraph.model.mxCell;
import java.util.HashMap;

/**
 *
 * @author Marcos e Adriel
 */
public class mxCellArestaDAO {

    HashMap<String, mxCell> arestas;

    /**
     * Método construtor
     */
    public mxCellArestaDAO() {
        arestas = new HashMap();
    }

    /**
     * Método de buscar aresta com base em dois nomes de vertices
     *
     * @param nomeVertice1 Nome do 1° vertice
     * @param nomeVertice2 Nome do 2° vertice
     * @return Retorna o método de retornar os nomes dos vertices buscados
     */
    public mxCell buscaAresta(String nomeVertice1, String nomeVertice2) {
        mxCell buscada = arestas.get(nomeVertice1 + "\n" + nomeVertice2);
        return buscada == null ? arestas.get(nomeVertice2 + "\n" + nomeVertice1) : buscada;
    }

    /**
     * Método de adicionar aresta
     *
     * @param nomeVertice1 Nome do 1° verticce
     * @param nomeVertice2 Nome do 2° vertice
     * @param novaAresta É o mxCell da nova aresta
     */
    public void adicionaArestaI(String nomeVertice1, String nomeVertice2, mxCell novaAresta) {
        if (this.buscaAresta(nomeVertice1, nomeVertice2) == null) {
            arestas.put(nomeVertice1 + "\n" + nomeVertice2, novaAresta);
        }
    }

    /**
     * Método de remover aresta
     *
     * @param emRemocao É a aresta a ser removida
     * @return Retorna o mxCell da remoção, se não retorna null
     */
    public mxCell removeArestaI(mxCell emRemocao) {
        for (Object keyAtual : arestas.keySet()) {
            if (arestas.get(keyAtual).equals(emRemocao)) {
                return arestas.remove((String) keyAtual);
            }
        }
        return null;
    }
}
