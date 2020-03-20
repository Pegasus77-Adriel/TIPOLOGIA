package Model;

import java.util.LinkedList;

/**
 *
 * @author Adryel e Marcos
 */
public class Grafo {

    private LinkedList<Vertice> listaVertices;
    private LinkedList<String> listaNomeVertices;

    // Metódo Construtor
    /**
     * Construtor responsável por inicializar a lista de vértices e a lista de
     * nome de vértices da classe
     */
    public Grafo() {
        this.listaVertices = new LinkedList();
        this.listaNomeVertices = new LinkedList();
    }

    /**
     * Método contrutor para clonagem
     *
     * @param listaVertices Lista de vertice para ser adicionada
     * @param listaNomeVertices Lista de nomes de verticies para ser adicionada
     */
    private Grafo(LinkedList<Vertice> listaVertices, LinkedList<String> listaNomeVertices) {
        this.listaVertices = listaVertices;
        this.listaNomeVertices = listaNomeVertices;
    }

    /**
     * Método de Adicionar Vertices no Grafo
     *
     * @param novoVertice Vertice a inserido no grafo
     * @return Retorna true ou false indicando se a operação foi bem sucedida ou
     * não
     */
    public boolean adicionaVertice(Vertice novoVertice) {
        if (verticeInedito(novoVertice)) {
            this.listaVertices.add(novoVertice);
            listaNomeVertices.add(novoVertice.getNome());
            return true;
        }
        return false; // retorna false caso o vertice ja exista
    }

    /**
     * Adiciona aresta não-orientada
     *
     * @param peso Peso da nova aresta
     * @param origem Vertice inicial da nova aresta
     * @param fim Vertice final da nova aresta
     * @return Retorna true para uma adição bem-sucessida ou false caso
     * contrário
     */
    public boolean adicionaArestaDupla(int peso, Vertice origem, Vertice fim) {
        if (origem == null || fim == null) {
            return false;
        }
        return origem.adicionaAresta(fim, peso) && fim.adicionaAresta(origem, peso);
    }

    /**
     * Adiciona uma aresta direciona de um vértice a outro
     *
     * @param peso Peso da aresta
     * @param origem Vértice-origem
     * @param fim Vértice-destino
     * @return Retona true para adição bem-sucessidade ou false caso contrário
     */
    public boolean adicionaArestaSimples(int peso, Vertice origem, Vertice fim) {
        if (origem == null || fim == null) {
            return false;
        }
        return origem.adicionaAresta(fim, peso);
    }

    /**
     * Fornece uma lista de vértices
     *
     * @return Retorna a lista de vértices {@link #listaVertices}
     */
    public LinkedList<Vertice> getVertices() {
        return listaVertices;
    }

    /**
     * Remove um vértice do grafo
     *
     * @param emRemocao Vértice que será removido
     * @return Retorna true para remoção bem-sucedida ou false caso contrário
     */
    public boolean removeVertice(Vertice emRemocao) {
        Vertice verticeAtual = null;
        for (int i = 0; i < listaVertices.size(); i++) {
            verticeAtual = listaVertices.get(i);
            if (!verticeAtual.equals(emRemocao)) {
                verticeAtual.removeLigacaoCom(emRemocao);
            }
        }
        return listaVertices.remove(emRemocao) && listaNomeVertices.remove(emRemocao.getNome());
    }

    /**
     * Remove um vértice do grafo
     *
     * @param nomeVertice Nome do vértice que será removido
     * @return Retorna true para uma remoção bem-sucedida ou false caso
     * contrário
     */
    public boolean removeVertice(String nomeVertice) {
        Vertice buscado = this.buscaVertice(nomeVertice);
        // A linha acima faz a chamada do método de encontrar o objeto vertice
        // com base no nome do vertice
        return this.removeVertice(buscado);
    }

    /**
     * Método de verificar se a lista dos vertices do grafo está vazia
     *
     * @return Retorna true ou false, caso a lista de vertices do grafo esteja
     * vazia ou nao
     */
    public boolean estaVazio() {
        return this.listaVertices.isEmpty();
    }

    /**
     * Método que busca um determinado vertice
     *
     * @param nomeVertice Nome do vertice a ser buscado
     * @return Retorna o verticie caso seja encontrado
     */
    public Vertice buscaVertice(String nomeVertice) {
        Vertice atual;
        for (int i = 0; i < listaVertices.size(); i++) {
            atual = listaVertices.get(i);
            if (atual.getNome().equals(nomeVertice)) {
                return atual;
            }
        }
        return null; // Retorna null caso não exista nenhum vertice 
        //com o nome buscado
    }

    /**
     * Método que analisa se o vertice ja existe
     *
     * @param novoVertice Vertice a ser analisado
     * @return Retorna true ou false indicando se o vertice já existe ou não
     */
    private boolean verticeInedito(Vertice novoVertice) {
        for (int i = 0;
                i < listaNomeVertices.size();
                i++) {
            if (listaNomeVertices.get(i).equals(novoVertice.getNome())) {
                return false;
            }
        }

        return true; // Retorna true caso o vertica já exista
    }

    /**
     * Método que remove a aresta entre dois vertices
     *
     * @param nomeVertice1 1° vertice
     * @param nomeVertice2 2° vertice
     * @return Retorna true ou false indicando se a operção foi bem sucedida ou
     * não
     */
    public boolean removeAresta(String nomeVertice1, String nomeVertice2) {
        Vertice vertice1, vertice2, verticeAtual;
        vertice1 = vertice2 = verticeAtual = null;
        for (int i = 0; i < listaVertices.size(); i++) {
            verticeAtual = listaVertices.get(i);
            if (verticeAtual.getNome().equals(nomeVertice1)) {
                vertice1 = verticeAtual;
            }
            else if (verticeAtual.getNome().equals(nomeVertice2)) {
                vertice2 = verticeAtual;
            }
            if (vertice1 != null && vertice2 != null) {
                return vertice1.removeLigacaoCom(vertice2) && vertice2.removeLigacaoCom(vertice1);
            }
        }
        return false; // Retorna false caso não tenha encontrado aresta
    }
}
