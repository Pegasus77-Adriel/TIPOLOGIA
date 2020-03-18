package Model;

import java.util.LinkedList;

public class Vertice {

    private String nome;
    private LinkedList<Aresta> arestas;
    private int distanciaOrigem;
    private Vertice antecessor;
    private boolean terminal;

    public Vertice(String nome, boolean terminal) {
        this.nome = nome;
        this.arestas = new LinkedList();
        this.terminal = terminal;
    }

    public boolean addAresta(Vertice destino, int peso) {
        return arestas.add(new Aresta(peso, destino));
    }

    public LinkedList<Aresta> getArestas() {
        return this.arestas;
    }

    public int getPesoLigacaoCom(Vertice adjacente) {
        for (int i = 0; i < arestas.size(); i++) {
            Aresta arestaAtual = arestas.get(i);
            Vertice verticeAtual = arestaAtual.getFim();
            if (verticeAtual.equals(adjacente)) {
                return arestaAtual.getPeso();
            }
        }
        return -1;
    }

    public String getNome() {
        return nome;
    }

    public void setDistanciaOrigem(int novaDistanciaOrigem) {
        this.distanciaOrigem = novaDistanciaOrigem;
    }

    public int getDistanciaOrigem() {
        return this.distanciaOrigem;
    }

    public void setVerticeAntecessor(Vertice novoAntecessor) {
        this.antecessor = novoAntecessor;
    }

    public Vertice getVerticeAntecessor() {
        return this.antecessor;
    }

    public boolean isTerminal() {
        return this.terminal;
    }

    public boolean removeLigacaoCom(Vertice emRemocao) {
        for (int i = 0; i < arestas.size(); i++) {
            Aresta arestaAtual = arestas.get(i);
            Vertice verticeAtual = arestaAtual.getFim();
            if (verticeAtual.equals(emRemocao)) {
                return arestas.remove(arestaAtual);
            }
        }
        return false;
    }
}
