package DAO;

import Model.*;
import java.util.LinkedList;

/**
 *
 * @author Adryel
 */
public class GrafoDAO {

    private static Grafo grafo = new Grafo();

    /**
     * Método que retorna o grafo
     *
     * @return Retorna o grafo
     */
    public Grafo getGrafo() {

        return this.grafo;
    }

    /**
     * Método de adicionar vertice
     *
     * @param novoVertice Novo vertice a ser inserido no grafo
     * @return Retorna método do objeto Grafo de adicionar vertice
     */
    public boolean adicionaVertice(Vertice novoVertice) {

        return grafo.adicionaVertice(novoVertice);

    }

    /**
     * Método de adicionar aresta dupla
     *
     * @param peso Peso da nova aresta
     * @param origem Vertice de partida da nova aresta
     * @param fim Vertice final da nova aresta
     * @return Retorna método do objeto Grafo de adicionar aresta dupla
     */
    public boolean adicionaArestaDupla(int peso, Vertice origem, Vertice fim) {

        return grafo.adicionaArestaDupla(peso, origem, fim);

    }

    /**
     * Método de adicionar aresta simples
     *
     * @param peso Peso da nova aresta
     * @param origem Vertice de partida da nova aresta
     * @param fim Vertice final da nova aresta
     * @return Retorna método do objeto Grafo de adicionar aresta simples
     */
    public boolean adicionaArestaSimples(int peso, Vertice origem, Vertice fim) {

        return grafo.adicionaArestaSimples(peso, origem, fim);

    }

    /**
     * Método de retornar lista de vertices do grafo
     *
     * @return Retorna lista de vertices do objeto Grafo
     */
    public LinkedList<Vertice> getVertices() {

        return grafo.getVertices();

    }

    /**
     * Método de remover Vertice do grafo com base no objeto Vertice
     *
     * @param emRemocao Vertice a ser removido
     * @return Retorna método de remover vertice do objeto Grafo
     */
    public boolean removeVertice(Vertice emRemocao) {

        return grafo.removeVertice(emRemocao);

    }

    /**
     * Método de remover vertice com base no nome do vertice
     *
     * @param nomeVertice Nome do vertice a ser removido
     * @return Retorna método de remover vertice do objeto Grafo
     */
    public boolean removeVertice(String nomeVertice) {

        return grafo.removeVertice(nomeVertice);

    }

    /**
     * Método verificar se a lista de vertice do grafo está vazia
     *
     * @return Retorna método verificar se a lista de vertice do grafo está
     * vazia do objeto Grafo
     */
    public boolean estaVazio() {

        return grafo.estaVazio();

    }

    /**
     * Método de buscar vertice com base no nome
     *
     * @param nomeVertice Nome do vertice a ser buscado
     * @return Retorna método de buscar vertice com base no nome do objeto Grafo
     */
    public Vertice buscaVertice(String nomeVertice) {

        return grafo.buscaVertice(nomeVertice);

    }

    /**
     * Método de remover aresta com base no nome
     *
     * @param nomeVertice1 Nome do vertice que contém a aresta
     * @param nomeVertice2 Nome do outro vertice que contém a aresta
     * @return Retorna método de remover aresta com base no nome do objeto Grafo
     */
    public boolean removeAresta(String nomeVertice1, String nomeVertice2) {
        return grafo.removeAresta(nomeVertice1, nomeVertice2);
    }
}
