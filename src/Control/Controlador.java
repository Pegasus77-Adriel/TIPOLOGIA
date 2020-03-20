package Control;

import DAO.GrafoDAO;
import Model.Aresta;
import Model.Arquivo;
import Model.Dijkstra;
import Model.Grafo;
import Model.Vertice;
import DAO.mxCellArestaDAO;
import DAO.mxCellVerticeDAO;
import Observer.ActionEventListenerAdicionaVertice;
import Observer.ActionEventListenerConectaVertices;
import Observer.ActionEventListenerExportaGrafo;
import Observer.ActionEventListenerImportaGrafo;
import Observer.ActionEventListenerRemove;
import Observer.ActionEventListenerRolagemMouse;
import Observer.ActionEventListenerSaida;
import Observer.ActionEventListenerSelecionaAparelho;
import Observer.EventosMouse;
import View.InterfacePrincipal;
import com.mxgraph.model.mxCell;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Adriel
 * @author Marcos Vinícius
 */
public class Controlador {

    private final int altura_vertice_Rooteador = 30;
    private final int largura_vertice_Rooteador = 50;
    private final int altura_vertice_Computador = 60;
    private final int largura_vertice_Computador = 100;
    private final int altura_vertice_Internet = 90;
    private final int largura_vertice_Internet = 200;

    private String tipo_aparelho = "computador";
    private mxCell primeiroVertice;
    private mxCell celulaSelecionada1;
    private mxCell celulaSelecionada2;
    private int xA = Integer.MIN_VALUE, xB = Integer.MIN_VALUE, yA, yB;

    private Collection<Object> listaCaminhos;
    private mxCellArestaDAO listaArestas;
    private mxCellVerticeDAO listaVertices;

    private InterfacePrincipal interfaceI;
    private GrafoDAO grafoDAO;

    /**
     * Construtor da classe e responsável por inicializar a interface visual
     * junto com as classes do DAO
     *
     * @param exibirInterface Caso seja true, a interface do programa estará
     * visível. O oposto disse acontece caso o valor seja false
     */
    public Controlador(boolean exibirInterface) {
        //Instânciando a interface:
        interfaceI = new InterfacePrincipal(new ActionEventListenerRemove(this), new ActionEventListenerSaida(),
                new ActionEventListenerExportaGrafo(this), new ActionEventListenerImportaGrafo(this),
                new ActionEventListenerConectaVertices(this), new ActionEventListenerSelecionaAparelho(this),
                new ActionEventListenerAdicionaVertice(this), new EventosMouse(this), new ActionEventListenerRolagemMouse(this));
        //Instânciando algumas classes que armazenarão dados:
        listaArestas = new mxCellArestaDAO();
        listaVertices = new mxCellVerticeDAO();
        grafoDAO = new GrafoDAO();
        listaCaminhos = new LinkedList();

        interfaceI.setVisible(exibirInterface); //Decide se a interface será ou não visível
    }

    /**
     * Adiciona um novo vértice no sistema a partir das informações enviadas
     * pelo usuário
     */
    public void adicionaVertice() {
        String tipoVertice = tipo_aparelho;
        String nomeVertice;
        //Há um tratamento especial para o vértice Internet:
        if (tipo_aparelho.equals("Internet")) {
            if (grafoDAO.buscaVertice("Internet") != null || listaVertices.contemVertice("Internet")) {
                interfaceI.exibeMensagem("Vértice não adicionado! Só é permitido 1 vértice 'Internet' no grafo");
                return;
            }
            nomeVertice = "Internet";
        }
        else {
            nomeVertice = interfaceI.recebeNomeVertice();
            if ("Internet".equals(nomeVertice)) {
                interfaceI.exibeMensagem("Operação abortada! O nome 'Internet' está reservado ao vértice Internet");
                return;
            }
        }

        Pattern conjuntoBits = Pattern.compile("\\w"); /*Compila um conjunto de bits correspondente 
        aos caracteres válidos ou visuais = (\\w)*/
        Matcher buscadorBits = conjuntoBits.matcher(nomeVertice); /*Inicia a verificação dentro desse conjunto de bits
        para saber se só há caractere na String "nomeVertices" que se encontram nesse grupo*/

        if (nomeVertice != null && buscadorBits.find()) { //Se o nome do vértice não for nulo e conter algum caractere visual, faça:
            Vertice novo = new Vertice(nomeVertice, tipoVertice.equalsIgnoreCase("computador")); 
            if (!grafoDAO.adicionaVertice(novo)) {
                interfaceI.exibeMensagem("O vértice '" + nomeVertice + "' não será adicionado porque já existe no sistema!");
            }
            else {
                insereVerticeInterface(nomeVertice, tipoVertice);
            }
        }
        else {
            interfaceI.exibeMensagem("Operação interrompida! Nome de vértice informado é inválido!");
        }
    }

    /**
     * Método responsável pela adição de arestas no sistema
     */
    public void adicionaAresta() {
        String[] informacoes = interfaceI.recebeInformacoesLigacao();
        Pattern executor1 = Pattern.compile("\\w");
        Matcher buscador1 = executor1.matcher(informacoes[0]);
        Matcher buscador2 = executor1.matcher(informacoes[1]);
        Pattern executor2 = Pattern.compile("\\D"); //Reúne um conjunto de bits 
        Matcher buscador3 = executor2.matcher(informacoes[2]);
        if (!buscador2.find() || !buscador1.find()) {
            interfaceI.exibeMensagem("Operação interrompida! Nome de vértice informado é inválido!");
            return;
        }
        if (buscador3.find()) {
            interfaceI.exibeMensagem("Operação interrompida! O valor do peso de ligação fornecido é inválido!");
            return;
        }
        int pesoLigacao = Integer.parseInt(informacoes[2]);
        Vertice v1 = grafoDAO.buscaVertice(informacoes[0]);
        Vertice v2 = grafoDAO.buscaVertice(informacoes[1]);
        if (!grafoDAO.adicionaArestaDupla(pesoLigacao, v1, v2)) {
            interfaceI.exibeMensagem("Aresta não adicionada! Verifique a existência dos vértices informados e de suas ligações!");
        }
        else {
            insereArestaInterface(informacoes[0], informacoes[1], informacoes[2]);
        }
    }

    private mxCell insereVerticeInterface(String nomeVertice, String tipoVertice) {
        mxCell novoVertice = null;
        if (listaVertices.buscaVertice(nomeVertice) == null) {
            if (tipoVertice.equalsIgnoreCase("internet")) {
                novoVertice = interfaceI.adicionaVertice(nomeVertice, largura_vertice_Internet, altura_vertice_Internet, tipoVertice);
            }
            else if (tipoVertice.equalsIgnoreCase("computador")) {
                novoVertice = interfaceI.adicionaVertice(nomeVertice, largura_vertice_Computador, altura_vertice_Computador, tipoVertice);
            }
            else if (tipoVertice.equalsIgnoreCase("rooteador")) {
                novoVertice = interfaceI.adicionaVertice(nomeVertice, largura_vertice_Rooteador, altura_vertice_Rooteador, tipoVertice);
            }
            listaVertices.adicionaVerticeI(nomeVertice, novoVertice);
            if (!listaVertices.estaVazio() && primeiroVertice != null) {
                interfaceI.adicionaAresta("", primeiroVertice, novoVertice, false);
            }
            else {
                primeiroVertice = novoVertice;
            }
            return novoVertice;
        }
        return null;
    }

    private mxCell insereArestaInterface(String nomeVertice1, String nomeVertice2, String nomeAresta) {
        if (listaArestas.buscaAresta(nomeVertice1, nomeVertice2) == null) {
            mxCell vertice1 = listaVertices.buscaVertice(nomeVertice1);
            mxCell vertice2 = listaVertices.buscaVertice(nomeVertice2);
            mxCell novaAresta = interfaceI.adicionaAresta(nomeAresta, vertice1, vertice2, true);
            listaArestas.adicionaArestaI(nomeVertice1, nomeVertice2, novaAresta);
            return novaAresta;
        }
        return null;
    }

    /**
     *
     */
    public void removeCelula() {
        mxCell celula = celulaSelecionada2 == null ? celulaSelecionada1 : celulaSelecionada2;
        if (celula != null) {
            if (celula.isVertex()) {
                removeArestasDeVertice(celula);
                removeVertice(celula);
                interfaceI.insereSelecao(null); //Tem o efeito colateral de descelecionar os vértices
                interfaceI.exibeMensagem("Vértice removido com sucesso!");
            }
        }
        celulaSelecionada1 = celulaSelecionada2 = null;
    }

    private mxCell removeVertice(mxCell vertice) {
        mxCell removido = interfaceI.removeCelula(vertice);
        grafoDAO.removeVertice((String) removido.getValue());
        listaVertices.removeVerticeI((String) removido.getValue());
        if (listaVertices.estaVazio()) {
            primeiroVertice = null;
        }
        return removido;
    }

    private void removeArestasDeVertice(mxCell vertex) {
        Vertice verticeBuscados = grafoDAO.buscaVertice((String) vertex.getValue());
        LinkedList<Aresta> listaAdjacentes = verticeBuscados.getArestas();
        while (vertex.getEdgeCount() > 0) {
            if (!listaAdjacentes.isEmpty()) {
                Vertice verticeAdjacente = listaAdjacentes.get(0).getFim();
                grafoDAO.removeAresta(verticeBuscados.getNome(), verticeAdjacente.getNome()); //Isso aqui pode ser tornar útil caso implantemos a opção de remover aresta na interface
            }
            listaArestas.removeArestaI((mxCell) vertex.getEdgeAt(0));
            interfaceI.removeCelula(vertex.getEdgeAt(0));
        }
    }

    /**
     *
     */
    public void exportaConfiguracoes() {
        String diretorio = interfaceI.selecionaDiretorioSalvamento();
        if (diretorio != null) {
            try {
                Arquivo.trasfereParaArquivo(diretorio, grafoDAO.getGrafo());
                interfaceI.exibeMensagem("As configurações foram salvas com sucesso!");
            }
            catch (IOException | ExceptionInInitializerError ex) {
                interfaceI.exibeMensagem("Houve um erro na criação do arquivo! Por favor, tente com outro diretório.");
            }
        }
        else {
            interfaceI.exibeMensagem("Operação cancelada! Nenhum diretorio foi informado!");
        }
    }

    /**
     *
     * @param x
     * @param y
     */
    public void cliqueEsquerdo(int x, int y) {
        mxCell selecionada = (mxCell) interfaceI.getAreaComponentes().getCellAt(x, y);

        if (selecionada == null) {
            celulaSelecionada1 = celulaSelecionada2 = null;
        }
        else if (selecionada.isEdge()) {
            celulaSelecionada1 = celulaSelecionada2 = null;
        }
        else {
            if (celulaSelecionada1 == null) {
                celulaSelecionada1 = selecionada;
                if (selecionada.isVertex()) {
                    LinkedList<Vertice> caminhos = obtemMenoresCaminhos(((String) selecionada.getValue()), false);
                    destacaCaminhosInterface(caminhos);
                }
            }
            else {
                if (!celulaSelecionada1.equals(selecionada) && celulaSelecionada2 == null) {
                    celulaSelecionada2 = selecionada;
                    removeSelecao(x, y);
                    LinkedList<Vertice> caminhos = obtemMenoresCaminhos((String) celulaSelecionada1.getValue(), true);
                    iniciaVerreduraSelecao(caminhos);
                }
                else {
                    celulaSelecionada1 = selecionada;
                    LinkedList<Vertice> caminhos = obtemMenoresCaminhos(((String) selecionada.getValue()), false);
                    destacaCaminhosInterface(caminhos);
                    celulaSelecionada2 = null;
                }
            }
        }
        if (xA == xB && xA == Integer.MIN_VALUE) {
            xA = x;
            yA = y;
        }
        else {
            xB = x;
            yB = y;
        }
        atualizaDistanciaEuclidiana();
    }

    /**
     *
     * @param x
     * @param y
     */
    public void removeSelecao(int x, int y) {
        mxCell selecionada = (mxCell) interfaceI.getAreaComponentes().getCellAt(x, y);
        interfaceI.removeCelecao(selecionada);
    }

    /**
     *
     */
    public void importaConfiguracoes() {
        int decisao = -1;
        if (!this.grafoDAO.estaVazio()) {
            decisao = interfaceI.exibeDialogoImportArquivo();
        }
        if (decisao != 2) {
            String diretorio = interfaceI.selecionaDiretorioAbertura();
            if (diretorio != null) {
                if (decisao == 0) {
                    for (Object verticeAtual : listaVertices.getVertices()) {
                        removeArestasDeVertice((mxCell) verticeAtual);
                        removeVertice((mxCell) verticeAtual);
                    }
                }
                try {
                    Arquivo.trasfereParaGrafo(diretorio, grafoDAO.getGrafo());
                    trasfereModelParaInterface(grafoDAO.getGrafo());
                    interfaceI.exibeMensagem("Suas configurações foram importadas com sucesso!");
                }
                catch (IOException | ExceptionInInitializerError ex) {
                    interfaceI.exibeMensagem("Houve um erro na criação do arquivo! Por favor, tente com outro diretório.");
                }
            }
            else {
                interfaceI.exibeMensagem("Operação cancelada! Nenhum diretorio foi informado!");
            }
        }
    }

    private void trasfereModelParaInterface(Grafo grafo) {
        LinkedList<Vertice> vertices = grafo.getVertices();
        for (int j = 0; j < vertices.size(); j++) {
            Vertice verticeAtual = vertices.get(j);
            String tipoVertice = "Internet";
            if (!"Internet".equals(verticeAtual.getNome())) {
                tipoVertice = verticeAtual.isTerminal() ? "computador" : "rooteador";
            }
            insereVerticeInterface(verticeAtual.getNome(), tipoVertice);
        }

        for (int j = 0; j < vertices.size(); j++) {
            Vertice verticeAtual = vertices.get(j);
            LinkedList<Aresta> arestas = verticeAtual.getArestas();
            for (int k = 0; k < arestas.size(); k++) {
                Aresta arestaAtual = arestas.get(k);
                String nomeVerticeDestino = arestaAtual.getFim().getNome();
                int pesoArestaAtual = arestaAtual.getPeso();
                insereArestaInterface(verticeAtual.getNome(), nomeVerticeDestino, pesoArestaAtual + "");
            }
        }
    }

    /**
     *
     * @param rotacaoRoda
     * @param ctrlPressionado
     */
    public void defineZoom(int rotacaoRoda, boolean ctrlPressionado) {
        if (ctrlPressionado) {
            if (rotacaoRoda > 0) {
                interfaceI.getAreaComponentes().zoomOut();
                interfaceI.getAreaComponentes().zoomAndCenter();
            }
            else {
                interfaceI.getAreaComponentes().zoomIn();
                interfaceI.getAreaComponentes().zoomAndCenter();
            }
        }
    }

    private void atualizaDistanciaEuclidiana() {
        String coordenada1, coordenada2, distanciaE;
        if (xA == Integer.MIN_VALUE) {
            coordenada1 = coordenada2 = distanciaE = "";
        }
        else {
            coordenada1 = "x= " + xA + ",   y= " + yA;
            if (xB == Integer.MIN_VALUE) {
                coordenada2 = distanciaE = "";
            }
            else {
                coordenada2 = "x= " + xB + ",   y= " + yB;
                double BC = Math.pow(yB - yA, 2);
                double AC = Math.pow(xB - xA, 2);
                double distancia = Math.sqrt(BC + AC);
                distanciaE = distancia + "";
                xA = xB = Integer.MIN_VALUE;
            }
        }
        interfaceI.exibeNovaDistanciaEuclidiana(coordenada1, coordenada2, distanciaE);
    }

    private void destacaCaminhosInterface(LinkedList<Vertice> caminhos) {
        String conjuntoListas = "";
        if (caminhos != null) { //Apenas por prevenção, mas caminhos nunca será null
            conjuntoListas += "Exibindo listas de caminhos para o vértice '" + caminhos.get(0).getNome() + "':  \n\n\n";
            listaCaminhos = new LinkedList();
            for (int j = 0; j < caminhos.size(); j++) {
                Vertice verticeAtual = caminhos.get(j);
                conjuntoListas += destacaAntecessores(verticeAtual, j + 1);
            }
            interfaceI.exibeListasCaminhos(conjuntoListas);
        }
    }

    private String destacaAntecessores(Vertice verticeAtual, int nCaminho) {
        String novoCaminho = verticeAtual == null || nCaminho == -1 ? "" : nCaminho + "º caminho: ";
        while (verticeAtual != null) {
            novoCaminho += verticeAtual.getNome();
            mxCell verticeEncontrado = listaVertices.buscaVertice(verticeAtual.getNome());
            extendeCaminho(verticeEncontrado);
            if (verticeAtual.getVerticeAntecessor() != null) {
                novoCaminho += ", " + " ";
                extendeCaminho(listaArestas.buscaAresta(verticeAtual.getNome(), verticeAtual.getVerticeAntecessor().getNome()));
            }
            verticeAtual = verticeAtual.getVerticeAntecessor();
        }
        novoCaminho += "\n";
        interfaceI.insereSelecao(listaCaminhos.toArray());
        return novoCaminho;
    }

    private void extendeCaminho(mxCell novaCelula) {
        if (!listaCaminhos.contains(novaCelula)) {
            listaCaminhos.add(novaCelula);
        }
    }

    /**
     *
     */
    public void defineItemSelecionado() {
        tipo_aparelho = interfaceI.getConteudoCombobox();
    }

    private LinkedList<Vertice> obtemMenoresCaminhos(String nomeVertice, boolean apenasTerminal) {
        Dijkstra d = new Dijkstra();
        Vertice buscado = grafoDAO.buscaVertice(nomeVertice);
        LinkedList<Vertice> gerado = (!apenasTerminal || buscado.isTerminal()) ? d.obtemMenoresCaminhos(buscado, grafoDAO.getVertices()) : null;
        return gerado;
    }

    private void iniciaVerreduraSelecao(LinkedList<Vertice> caminhos) {
        Vertice v1, v2;
        v1 = v2 = null;
        if (caminhos != null) {
            for (int j = 0; j < caminhos.size(); j++) {
                Vertice verticeAtual = caminhos.get(j);
                if (verticeAtual.getNome().equals(celulaSelecionada2.getValue())) {
                    v2 = verticeAtual;
                }
                else if (verticeAtual.getNome().equals(celulaSelecionada1.getValue())) {
                    v1 = verticeAtual;
                }
                if (v1 != null && v2 != null) {
                    if (v1.isTerminal() && v2.isTerminal()) {
                        listaCaminhos = new LinkedList();
                        destacaAntecessores(verticeAtual, -1);
                        break;
                    }
                }
            }
        }
    }
}
