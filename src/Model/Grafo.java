package Model;

import java.util.LinkedList;

public class Grafo {

    private LinkedList<Vertice> listaVertices;
    private LinkedList<String> listaNomeVertices;

    // Metódo Construtor
    public Grafo() {
        this.listaVertices = new LinkedList();
        this.listaNomeVertices = new LinkedList();
    }

    // Método Contrutor para Clonagem
    private Grafo(LinkedList<Vertice> listaVertices, LinkedList<String> listaNomeVertices) {
        this.listaVertices = listaVertices;
        this.listaNomeVertices = listaNomeVertices;
    }

    // Metódo de Adicionar Vertices no Grafo
    public boolean adicionaVertice(Vertice novoVertice) {
        if (verticeInedito(novoVertice)) {
            this.listaVertices.add(novoVertice);
            listaNomeVertices.add(novoVertice.getNome());
            return true;
        }
        return false;
    }

    // Metódo de Adicionar uma Aresta ao Grafo
    public boolean adicionaArestaDupla(int peso, Vertice origem, Vertice fim) {
        if (origem == null || fim == null) {
            return false;
        }
        return origem.addAresta(fim, peso) && fim.addAresta(origem, peso);
    }

    public boolean adicionaArestaSimples(int peso, Vertice origem, Vertice fim) {
        return origem.addAresta(fim, peso);
    }

    // Método que Retorna Umas Lista de Todos os Verticies Contido no Grafo
    public LinkedList<Vertice> getVertices() {
        return listaVertices;
    }

    // Método de Excluir um Vertice no Grafo
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

    public boolean removeVertice(String nomeVertice) {
        Vertice buscado = this.buscaVertice(nomeVertice);
        return this.removeVertice(buscado);
    }

    public boolean estaVazio() {
        return this.listaVertices.isEmpty();
    }

    

    public Grafo Clone() {
        return new Grafo((LinkedList<Vertice>) listaVertices.clone(), (LinkedList<String>) listaNomeVertices.clone());
    }

    public Vertice buscaVertice(String nomeVertice) {
        Vertice atual;
        for (int i = 0; i < listaVertices.size(); i++) {
            atual = listaVertices.get(i);
            if (atual.getNome().equals(nomeVertice)) {
                return atual;
            }
        }
        return null;
    }

    private boolean verticeInedito(Vertice novoVertice) {
        for (int i = 0; i < listaNomeVertices.size(); i++) {
            if (listaNomeVertices.get(i).equals(novoVertice.getNome())) {
                return false;
            }
        }
        return true;
    }

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
                vertice1.removeLigacaoCom(vertice2);
                vertice2.removeLigacaoCom(vertice1);
                return true;
            }

        }
        return false;
    }
    
    public LinkedList<Vertice> getListaVertices() {
        return listaVertices;
    }

    public void setListaVertices(LinkedList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public LinkedList<String> getListaNomeVertices() {
        return listaNomeVertices;
    }

    public void setListaNomeVertices(LinkedList<String> listaNomeVertices) {
        this.listaNomeVertices = listaNomeVertices;
    }
}
