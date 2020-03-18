package DAO;

import Model.*;
import java.util.LinkedList;

public class GrafoDAO {
    
    private static Grafo grafo = new Grafo();
    
    
    public Grafo getGrafo(){
        
        return this.grafo;
    }
    
    public boolean adicionaVertice(Vertice novoVertice) {
       
        return grafo.adicionaVertice(novoVertice);
    
    }
    
    public boolean adicionaArestaDupla(int peso, Vertice origem, Vertice fim) {
        
        return grafo.adicionaArestaDupla(peso, origem, fim);
    
    }
    
    public boolean adicionaArestaSimples(int peso, Vertice origem, Vertice fim) {
         
        return grafo.adicionaArestaSimples(peso, origem, fim);
    
    }
    
    
    public LinkedList<Vertice> getVertices() {
         
        return grafo.getVertices();
         
    }
    
    public boolean removeVertice(Vertice emRemocao) {
        
        return grafo.removeVertice(emRemocao);
    
    }
    
    public boolean removeVertice(String nomeVertice) {
    
        return grafo.removeVertice(nomeVertice);
        
    }
    
    public boolean estaVazio() {
        
        return grafo.estaVazio();
    
    }
    
    public Grafo Clone() {
        
        return grafo.Clone();
        
    }
    
    public Vertice buscaVertice(String nomeVertice) {
        
        return grafo.buscaVertice(nomeVertice);
        
    }
    
    public boolean removeAresta(String nomeVertice1, String nomeVertice2) {
        
        return grafo.removeAresta(nomeVertice1, nomeVertice2);
         
    }
    
    public void setListaVertices(LinkedList<Vertice> listaVertices) {
        
        grafo.setListaVertices(listaVertices);
         
    }
    
    public void setListaNomeVertices(LinkedList<String> listaNomeVertices) {
        
        grafo.setListaNomeVertices(listaNomeVertices);
    
    }
    
    public LinkedList<Vertice> getListaVertices() {
         
        return grafo.getListaVertices();
         
    }
    
    public LinkedList<String> getListaNomeVertices() {
        
        return grafo.getListaNomeVertices();
        
    }
    
    
     
}