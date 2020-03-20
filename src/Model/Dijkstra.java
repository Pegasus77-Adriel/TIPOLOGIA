package Model;

import java.util.LinkedList;

/**
 *
 * @author Adryel
 */
public class Dijkstra {

    private LinkedList<Vertice> distanciaQ; //Lista de vértices antes de passar pelo Dijkstra
    private LinkedList<Vertice> distanciaS; //Lista de vértices depois de passar pelo Dijkstra
    private LinkedList<Vertice> listaVisitados;

    /**
     * Recebe um vertice de partida e o grafo, e encontra o menor caminho
     *
     * @param pontoPartida Vertice do ponto de partida
     * @param conjuntoVertices Grafo com o conjunto de todos os vertices
     *
     * @return A sequência dos vertices do menor caminho
     */
    public LinkedList<Vertice> obtemMenoresCaminhos(Vertice pontoPartida, LinkedList<Vertice> conjuntoVertices) {
        if (conjuntoVertices.isEmpty()) {
            return null; // Retorna nulo se o grafo estiver vazio

        }
        listaVisitados = new LinkedList();
        distanciaQ = conjuntoVertices;
        reinicializaNos(pontoPartida, distanciaQ);
        // A linha acima inicia a lista de custos do vertice de partida
        // em relação todos os outros

        distanciaS = new LinkedList();
        while (!distanciaQ.isEmpty()) {
            Vertice minimo = extraiMinimo(distanciaQ);
            if (minimo == null) {
                return distanciaS;
            }
            distanciaS.add(minimo);
            Aresta[] arestasVerticeMinimo = constroiVetorArestas(minimo);
            for (int i = 0; i < arestasVerticeMinimo.length; i++) {
                Vertice adjcenteAtual = arestasVerticeMinimo[i].getFim();
                int pesoLigacao = arestasVerticeMinimo[i].getPeso();
                if (adjcenteAtual.getDistanciaOrigem() > minimo.getDistanciaOrigem() + pesoLigacao) {
                    adjcenteAtual.setDistanciaOrigem(minimo.getDistanciaOrigem() + pesoLigacao);
                    adjcenteAtual.setVerticeAntecessor(minimo);
                }
            }
        }
        return distanciaS;
    }

    /**
     * Método que reinicializa a lista de custos dos vertices
     *
     * @param origem Vertice referencial
     * @param distanciaQ Lista de custos
     */
    private void reinicializaNos(Vertice origem, LinkedList<Vertice> distanciaQ) {
        LinkedList<Vertice> vertices = distanciaQ;
        for (int i = 0; i < vertices.size(); i++) {
            Vertice atual = vertices.get(i);
            if (atual.equals(origem)) {
                atual.setDistanciaOrigem(0);
                atual.setVerticeAntecessor(null);
            }
            else {
                int pesoLigacaoOrigem = atual.getPesoLigacaoCom(origem);
                if (pesoLigacaoOrigem > -1) {
                    atual.setDistanciaOrigem(pesoLigacaoOrigem);
                    atual.setVerticeAntecessor(origem);
                }
                else {
                    atual.setDistanciaOrigem(Integer.MAX_VALUE);
                    atual.setVerticeAntecessor(null);
                }
            }
        }
    }

    /**
     * Método que busca o vertice com a menor distância
     *
     * @param distanciaQ
     * @return Retorna o vertice com a menor distância
     */
    private Vertice extraiMinimo(LinkedList<Vertice> distanciaQ) {
        if (!distanciaQ.isEmpty()) {
            LinkedList<Vertice> vertices = distanciaQ;
            Vertice minimo, verticeAtual;
            minimo = null;
            int menorDistancia = Integer.MAX_VALUE;
            for (int i = 0; i < vertices.size(); i++) {
                verticeAtual = vertices.get(i);
                int distanciaVerticeAtual = verticeAtual.getDistanciaOrigem();
                if (distanciaVerticeAtual < menorDistancia && !listaVisitados.contains(verticeAtual)) {
                    menorDistancia = distanciaVerticeAtual;
                    minimo = verticeAtual;
                }
            }
            listaVisitados.add(minimo);
            return minimo;
        }
        return null; // Retorna null caso não haja vertices
    }

    /**
     * Método que constroi vetores de aresta
     *
     * @param minimo Verticie com distância menor
     * @return Retorna um vetor com o tamanho da aresta
     */
    private Aresta[] constroiVetorArestas(Vertice minimo) {
        int tamanho = minimo.getArestas().size();
        Aresta[] vetor = new Aresta[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = minimo.getArestas().get(i);
        }
        return vetor;
    }

}
