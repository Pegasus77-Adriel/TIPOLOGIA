package Model;

/**
 *
 * @author Adryel e Marcos
 */
public class Aresta {
    
    private  int peso;
    private  Vertice fim;
    

    /**
     * Retorna o peso da aresta
     * @return Retorna o peso da aresta
     */
    public int getPeso() {
        return peso;
    }
    

    /**
     * Metódo Construtor
     * @param peso Peso para nova aresta
     * @param fim Vertice final para nova aresta
     */
    public Aresta(int peso, Vertice fim) {
        
        this.peso = peso;
        this.fim = fim;
    }
    

   

    /**
     * Retorna o vertice final da aresta
     * @return Retorna vertice final aresta
     */
    public Vertice getFim() {
        return fim;
    }
    

  
}
