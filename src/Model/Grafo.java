package Model;

import java.util.LinkedList;

/**
 *
 * @author Adryel
 */
public class Grafo {

    private LinkedList<Vertice> listaVertices;
    private LinkedList<String> listaNomeVertices;


    /**
     * Metódo Construtor
     */
    public Grafo() {
        this.listaVertices = new LinkedList();
        this.listaNomeVertices = new LinkedList();
    }

    /**
     * Método contrutor para clonagem
     * @param listaVertices Lista de vertice para ser adicionada
     * @param listaNomeVertices Lista de nomes de verticies para ser adicionada
     */
    
    private Grafo(LinkedList<Vertice> listaVertices, LinkedList<String> listaNomeVertices) {
        this.listaVertices = listaVertices;
        this.listaNomeVertices = listaNomeVertices;
    }

    /** 
     * Método de Adicionar Vertices no Grafo
     * @param novoVertice Vertice a inserido no grafo
     * @return Retorna true ou false indicando se a operação
     * foi bem sucedida ou não
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
     * Método de adicionar aresta não orientada
     * @param peso Peso da nova aresta
     * @param origem Vertice inicial da nova aresta
     * @param fim Vertice fianl da nova aresta
     * @return Retorna false caso um do vertices seja null
     */
    public boolean adicionaArestaDupla(int peso, Vertice origem, Vertice fim) {
        if (origem == null || fim == null) {
            return false;
        }
        return origem.addAresta(fim, peso) && fim.addAresta(origem, peso);
    }
   

    /**
     * Método para adicionar nova aresta 
     * @param peso Peso da nova aresta
     * @param origem Vertice inicial da nova aresta
     * @param fim Vertice final da nova aresta
     * @return Retorna o método do objeto aresta de adicionar um nova aresta
     */
    public boolean adicionaArestaSimples(int peso, Vertice origem, Vertice fim) {
        return origem.addAresta(fim, peso);
    }


    /**
     * Método que retorna umas lista de todos os verticies contido no grafo
     * @return Retorna todos vertice do  grafo
     */
    public LinkedList<Vertice> getVertices() {
        return listaVertices;
    }

  
    /**
     * Método de excluir um vertice no grafo
     * @param emRemocao Vertice que será removido
     * @return Retorna método de remover vertice da lista de vertices do grafo
     * e retorna método de remover nome da lista de nomes de vertices do grafo
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
     * Método que remover vertice do grafo, com base no nome do vertice
     * @param nomeVertice Nome do vertice a ser removido
     * @return Retorna método de remover vertice, com base no objeto vertice
     */
    public boolean removeVertice(String nomeVertice) {
        Vertice buscado = this.buscaVertice(nomeVertice);
        // A linha acima faz a chamada do método de encontrar o objeto vertice
        // com base no nome do vertice
        return this.removeVertice(buscado);
    }

    /**
     * Método de verificar se a lista dos vertices do grafo está vazia
     * @return Retorna true ou false, caso a lista de vertices do grafo
     * esteja vazia ou nao
     */
    public boolean estaVazio() {
        return this.listaVertices.isEmpty();
    }

    /**
     * Método que criar um clone de um determinado grafo
     * @return Retorna o clone do grafo
     */
    public Grafo Clone() {
        return new Grafo((LinkedList<Vertice>) listaVertices.clone(), (LinkedList<String>) listaNomeVertices.clone());
    }
    /**
     * Método que busca um determinado vertice
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
     * @param novoVertice Vertice a ser analisado
     * @return Retorna true ou false indicando se o vertice já existe ou não
     */
    private boolean verticeInedito(Vertice novoVertice) {
        for (int i = 0; i < listaNomeVertices.size(); i++) {
            if (listaNomeVertices.get(i).equals(novoVertice.getNome())) {
                return false;
            }
        }
        return true; // Retorna true caso o vertica já exista
    }
    /**
     * Método que remove a aresta entre dois vertices
     * @param nomeVertice1 1° vertice
     * @param nomeVertice2 2° vertice
     * @return Retorna true ou false indicando se a operção foi
     * bem sucedida ou não
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
                vertice1.removeLigacaoCom(vertice2);
                vertice2.removeLigacaoCom(vertice1);
                return true;
            }

        }
        return false; // Retorna false caso não tenha encontrado aresta
    }
   
    
    /**
     * Método que retorna lista de vertices do grafo
     * @return Retorna lista de vertices do grafo
     */
    public LinkedList<Vertice> getListaVertices() {
        return listaVertices;
    }
   
    /**
     * Método que altera a lista de vertices do grafo
     * @param listaVertices Lista de vertices a inserida no grafo
     */
    public void setListaVertices(LinkedList<Vertice> listaVertices) {
        this.listaVertices = listaVertices;
    }
    
    /**
     * Método que retorna a lista de nomes dos vertices do grafo
     * @return Retorna lista de nomes de vertices do grafo
     */
    public LinkedList<String> getListaNomeVertices() {
        return listaNomeVertices;
    }
  

    /**
     * Método que altera a lista de nomes do vertice
     * @param listaNomeVertices Lista de nomes de vertices a ser inserida
     */
    public void setListaNomeVertices(LinkedList<String> listaNomeVertices) {
        this.listaNomeVertices = listaNomeVertices;
    }
}
