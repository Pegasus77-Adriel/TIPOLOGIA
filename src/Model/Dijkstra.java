package Model;

import java.util.LinkedList;


/**
 *
 * @author Adryel
 */
public class Dijkstra {

    private Grafo distanciaQ; //Lista de vértices antes de passar pelo Dijkstra
    private Grafo distanciaS; //Lista de vértices depois de passar pelo Dijkstra
    private LinkedList <Vertice> listaVisitados;

    public Grafo obtemMenoresCaminhos(Vertice pontoPartida, Grafo conjuntoVertices) {
        if (conjuntoVertices.estaVazio()) {
            return null;
        }
        listaVisitados = new LinkedList();
        distanciaQ = conjuntoVertices;
        reinicializaNos(pontoPartida, distanciaQ);
        distanciaS = new Grafo();
        while (!distanciaQ.estaVazio()) {
            Vertice minimo = extraiMinimo(distanciaQ);
            if (minimo == null) {
                return distanciaS;
            }
            distanciaS.adicionaVertice(minimo);
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

    private void reinicializaNos(Vertice origem, Grafo distanciaQ) {
        LinkedList<Vertice> vertices = distanciaQ.getVertices();
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

    private Vertice extraiMinimo(Grafo distanciaQ) {
        if (!distanciaQ.estaVazio()) {
            LinkedList<Vertice> vertices = distanciaQ.getVertices();
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
        return null;
    }

    private Aresta[] constroiVetorArestas(Vertice minimo) {
        int tamanho = minimo.getArestas().size();
        Aresta[] vetor = new Aresta[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = minimo.getArestas().get(i);
        }
        return vetor;
    }

}
