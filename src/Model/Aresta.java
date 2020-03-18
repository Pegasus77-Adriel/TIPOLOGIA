package Model;

public class Aresta {
    
    private  int peso;
    private  Vertice fim;
    
    // Retorna o Peso da Aresta
    public int getPeso() {
        return peso;
    }
    
    // Metódo Construtor
    public Aresta(int peso, Vertice fim) {
        
        this.peso = peso;
        this.fim = fim;
    }
    

    // Retorna o Vertice Final da Aresta
    public Vertice getFim() {
        return fim;
    }
    

  
}
