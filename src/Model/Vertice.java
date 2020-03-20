package Model;

import java.util.LinkedList;

/**
 *
 * @author Adryel
 */
public class Vertice {

    private String nome;
    private LinkedList<Aresta> arestas;
    private int distanciaOrigem;
    private Vertice antecessor;
    private boolean terminal;

    /**
     * Método Construtor
     *
     * @param nome Nome para o novo vertice
     * @param terminal Status de terminal para o novo vertice
     */
    public Vertice(String nome, boolean terminal) {
        this.nome = nome;
        this.arestas = new LinkedList();
        this.terminal = terminal;
    }

    /**
     * Método para adicionar uma nova aresta a dois vertices
     *
     * @param destino Verticie de destino o qual será o fim da aresta
     * @param peso Peso da aresta
     * @return
     */
    public boolean adicionaAresta(Vertice destino, int peso) {
        return (destino == null) ? false : arestas.add(new Aresta(peso, destino));
    }

    /**
     * Método que retorna a lista de aresta do vertice
     *
     * @return Retorna a lista de aresta do vertice
     */
    public LinkedList<Aresta> getArestas() {
        return this.arestas;
    }

    /**
     * Método que retorna o peso de uma aresta com base em um vertice adjacente
     *
     * @param adjacente Vertice adjacente de outro vertice
     * @return Retorna o peso da aresta
     */
    public int getPesoLigacaoCom(Vertice adjacente) {
        for (int i = 0; i < arestas.size(); i++) {
            Aresta arestaAtual = arestas.get(i);
            Vertice verticeAtual = arestaAtual.getFim();
            if (verticeAtual.equals(adjacente)) {
                return arestaAtual.getPeso();
            }
        }
        return -1; // O Método retorna -1 caso não haja ligação
    }

    /**
     * Método retorna o nome do vertice
     *
     * @return Retorna o nome do vertice
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que altera a distância de origem do vertice
     *
     * @param novaDistanciaOrigem Nova distância de origem para ser adiconada
     */
    public void setDistanciaOrigem(int novaDistanciaOrigem) {
        this.distanciaOrigem = novaDistanciaOrigem;
    }

    /**
     * Método que retorna a distancia da origem do vertice
     *
     * @return Retorna a distância de origem do vertice
     */
    public int getDistanciaOrigem() {
        return this.distanciaOrigem;
    }

    /**
     * Método que altera o vertice antecessor de um vertice
     *
     * @param novoAntecessor Novo vertice antecessor
     */
    public void setVerticeAntecessor(Vertice novoAntecessor) {
        this.antecessor = novoAntecessor;
    }

    /**
     * Método que retorna vertice antecessor de um vertice
     *
     * @return Retorna o vertice antecessor do vertice
     */
    public Vertice getVerticeAntecessor() {
        return this.antecessor;
    }

    /**
     * Método que retorna se o vertice é terminal ou não
     *
     * @return Retorna o status de terminal do vertice
     */
    public boolean isTerminal() {
        return this.terminal;
    }

    /**
     * Método que remove aresta entre dois vertices
     *
     * @param emRemocao Vertice o qual será removido a ligação
     * @return Retorna true ou false indicando se a operação foi bem sucedida ou
     * não
     */
    public boolean removeLigacaoCom(Vertice emRemocao) {
        for (int i = 0; i < arestas.size(); i++) {
            Aresta arestaAtual = arestas.get(i);
            Vertice verticeAtual = arestaAtual.getFim();
            if (verticeAtual.equals(emRemocao)) {
                return arestas.remove(arestaAtual);
            }
        }
        return false; // retorna false caso não haja ligação
    }
}
