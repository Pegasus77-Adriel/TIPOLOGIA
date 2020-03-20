package Controll;

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
    private mxCellArestaDAO listaArestasmxCell;
    private mxCellVerticeDAO listaVerticesMxCell;

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
        listaArestasmxCell = new mxCellArestaDAO();
        listaVerticesMxCell = new mxCellVerticeDAO();
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
            if (grafoDAO.buscaVertice("Internet") != null || listaVerticesMxCell.contemVertice("Internet")) {
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

        Pattern conjuntoBits = Pattern.compile("\\w");
        /*Compila um conjunto de bits correspondente 
        aos caracteres válidos ou visuais = (\\w)*/
        Matcher buscadorBits = conjuntoBits.matcher(nomeVertice);
        /*Inicia a verificação dentro desse conjunto de bits
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

    /**
     * Chama um conjunto de métodos que inserirão um vértice na interface
     * gráfica
     *
     * @param nomeVertice Nome do vértice que será inserido
     * @param tipoVertice Identificação do vértice (computador, internet ou
     * rooteador)
     * @return Retorna o vértice adicionado se ele o for ou senão, retorna null
     */
    private mxCell insereVerticeInterface(String nomeVertice, String tipoVertice) {
        mxCell novoVertice = null;
        if (listaVerticesMxCell.buscaVertice(nomeVertice) == null) {
            if (tipoVertice.equalsIgnoreCase("internet")) {
                novoVertice = interfaceI.adicionaVertice(nomeVertice, largura_vertice_Internet, altura_vertice_Internet, tipoVertice);
            }
            else if (tipoVertice.equalsIgnoreCase("computador")) {
                novoVertice = interfaceI.adicionaVertice(nomeVertice, largura_vertice_Computador, altura_vertice_Computador, tipoVertice);
            }
            else if (tipoVertice.equalsIgnoreCase("rooteador")) {
                novoVertice = interfaceI.adicionaVertice(nomeVertice, largura_vertice_Rooteador, altura_vertice_Rooteador, tipoVertice);
            }
            listaVerticesMxCell.adicionaVerticeI(nomeVertice, novoVertice);
            if (!listaVerticesMxCell.estaVazio() && primeiroVertice != null) {
                interfaceI.adicionaAresta("", primeiroVertice, novoVertice, false);
            }
            else {
                primeiroVertice = novoVertice;
            }
            return novoVertice;
        }
        return null;
    }

    /**
     * Invoca um conjunto de métodos que insirirão uma aresta na interface
     * gráfica
     *
     * @param nomeVertice1 Nome do vértice que conterá a aresta
     * @param nomeVertice2 Nome do vértice que conterá a aresta
     * @param nomeAresta Nome correspondente ao peso da aresta (peso da ligação)
     * @return Caso a aresta sera inserida, ela será retornada, senão, o retorno
     * é null
     */
    private mxCell insereArestaInterface(String nomeVertice1, String nomeVertice2, String nomeAresta) {
        if (listaArestasmxCell.buscaAresta(nomeVertice1, nomeVertice2) == null) {
            mxCell vertice1 = listaVerticesMxCell.buscaVertice(nomeVertice1);
            mxCell vertice2 = listaVerticesMxCell.buscaVertice(nomeVertice2);
            mxCell novaAresta = interfaceI.adicionaAresta(nomeAresta, vertice1, vertice2, true);
            listaArestasmxCell.adicionaArestaI(nomeVertice1, nomeVertice2, novaAresta);
            return novaAresta;
        }
        return null;
    }

    /**
     * Invoca um conjunto de métodos que removerão uma célula da interface e do
     * sistema através de suas coordenadas
     */
    public void removeCelula() {
        //Procurando pela última célula selecionada: 
        mxCell celula = celulaSelecionada2 == null ? celulaSelecionada1 : celulaSelecionada2;
        //Se alguma célula foi selecionada antes do clique no botão de exluir, faça:
        if (celula != null) {
            if (celula.isVertex()) { //Se um vértice foi selecionado, faça:
                removeArestasDeVertice(celula);
                removeVertice(celula);
                interfaceI.insereSelecao(null); //Tem o efeito colateral de descelecionar os vértices
                interfaceI.exibeMensagem("Vértice removido com sucesso!");
            }
        }
        celulaSelecionada1 = celulaSelecionada2 = null;
    }

    /**
     * Realiza a remoção de um vértice do sistema
     *
     * @param vertice Vértice que será removido
     * @return Retorna a mxCell vértice que for removida, caso contrário,
     * retorna null
     */
    private mxCell removeVertice(mxCell vertice) {
        mxCell removido = interfaceI.removeCelula(vertice);
        grafoDAO.removeVertice((String) removido.getValue());
        listaVerticesMxCell.removeVerticeI((String) removido.getValue());
        if (listaVerticesMxCell.estaVazio()) {
            primeiroVertice = null;
        }
        return removido;
    }

    /**
     * Invoca um conjunto de métodos que serão responsáveis por remover todas as
     * arestas de um vértice
     *
     * @param vertex Vértice que terá suas arestas removidas
     */
    private void removeArestasDeVertice(mxCell vertex) {
        Vertice verticeBuscados = grafoDAO.buscaVertice((String) vertex.getValue());
        LinkedList<Aresta> listaAdjacentes = verticeBuscados.getArestas();
        while (vertex.getEdgeCount() > 0) { //Enquanto o vértice possuir arestas, faça:
            if (!listaAdjacentes.isEmpty()) {
                Vertice verticeAdjacente = listaAdjacentes.get(0).getFim();
                grafoDAO.removeAresta(verticeBuscados.getNome(), verticeAdjacente.getNome()); //Isso aqui pode ser tornar útil caso implantemos a opção de remover aresta na interface
            }
            listaArestasmxCell.removeArestaI((mxCell) vertex.getEdgeAt(0));
            interfaceI.removeCelula(vertex.getEdgeAt(0));
        }
    }

    /**
     * Responsável por intermediar a exportação de um arquivo de configuração
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
     * Responsável por tratar o clique esquerdo do mause na interface gráfica,
     * desencadeado ações adequadas
     *
     * @param x Coordenada x (horizontal) de onde o clique ocorreu
     * @param y Coordenada y (vertical) de onde o clique ocorreu
     */
    public void cliqueEsquerdo(int x, int y) {
        mxCell selecionada = (mxCell) interfaceI.getAreaComponentes().getCellAt(x, y);

        if (selecionada == null) { //Se o clique for em um espaço em branco, faça:
            celulaSelecionada1 = celulaSelecionada2 = null; //Anula qualquer informação de células selecionadas anteriormente
        }
        else if (selecionada.isEdge()) { //Se o clique for em um vértice da interface gráfica, faça:
            celulaSelecionada1 = celulaSelecionada2 = null; //Anula qualquer informação de células selecionadas anteriormente
        }
        else { //Se algum vértice for selecionado, faça:
            if (celulaSelecionada1 == null) { //Se for o primeiro vértice selecionado, faça: 
                celulaSelecionada1 = selecionada;
                //Buscando e exibindo os menores caminhos na interface gráfica:
                LinkedList<Vertice> caminhos = obtemMenoresCaminhos(((String) selecionada.getValue()), false);
                destacaCaminhosInterface(caminhos);
            }
            else {
                if (!celulaSelecionada1.equals(selecionada) && celulaSelecionada2 == null) {//Se for uma segunda célula selecionada, faça:
                    celulaSelecionada2 = selecionada;
                    removeSelecao(x, y); //Desceleciona a célula atual
                    //Destacando o menor caminho entre duas células que foram selecionadas:
                    LinkedList<Vertice> caminhos = obtemMenoresCaminhos((String) celulaSelecionada1.getValue(), true);
                    iniciaVerreduraSelecao(caminhos);
                }
                else { //A primeira célula celecionada é descartada e a etapa da linha 287 é repetida, mas com outros detalhes
                    celulaSelecionada1 = selecionada;
                    LinkedList<Vertice> caminhos = obtemMenoresCaminhos(((String) selecionada.getValue()), false);
                    destacaCaminhosInterface(caminhos);
                    celulaSelecionada2 = null;
                }
            }
        }
        atualizaDistanciaEuclidiana(x, y);
    }

    /**
     * Desleciona um vértice
     *
     * @param x Coordenada horizontal do vértice
     * @param y Coordeanda vertical do vértice
     */
    public void removeSelecao(int x, int y) {
        mxCell selecionada = (mxCell) interfaceI.getAreaComponentes().getCellAt(x, y);
        interfaceI.removeCelecao(selecionada);
    }

    /**
     * Intermedia a importação de um arquivo de configuração
     */
    public void importaConfiguracoes() {
        int decisao = -1;
        if (!this.grafoDAO.estaVazio()) {
            decisao = interfaceI.exibeDialogoImportArquivo();
        }
        if (decisao != 2) { //Se o usuário não "Cancelar", faça:
            String diretorio = interfaceI.selecionaDiretorioAbertura();
            if (diretorio != null) { //Se o usuário realmente escolher um arquivo, faça:
                if (decisao == 0) { //Se o usuário escolher "substituir", faça:
                    for (Object verticeAtual : listaVerticesMxCell.getVertices()) {
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

    /**
     * Insere, na interface, todos os vértices cujos dados estão armazenados no
     * grafoModel, junto com suas respectivas ligações
     *
     * @param grafo Estrutura que terá seus dados analisados para que a
     * interface possa ser preenchida
     */
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
     * Regula o zoom na interface gráfica através de eventos do mouse
     *
     * @param rotacaoRoda Valor correspondente à direção em que a roda do mouse
     * foi rolada
     * @param ctrlPressionado Informação correspondente ao pressionamento da
     * tecla ctrl
     */
    public void defineZoom(int rotacaoRoda, boolean ctrlPressionado) {
        if (ctrlPressionado) {
            if (rotacaoRoda > 0) { //Se a roda foi girada pra baixo, faça:
                interfaceI.reduzZoom();
            }
            else { //Se a roda foi giradada pra cima, faça:
                interfaceI.aumentaZoom();
            }
        }
    }

    /**
     * Calcula e informa a distância euclidiana
     *
     * @param novoX Coordenada horizontal
     * @param novoY
     */
    private void atualizaDistanciaEuclidiana(int novoX, int novoY) {
        if (xA == xB && xA == Integer.MIN_VALUE) {
            xA = novoX;
            yA = novoY;
        }
        else {
            xB = novoX;
            yB = novoY;
        }
        String coordenada1, coordenada2, distanciaE;
        if (xA == Integer.MIN_VALUE) {
            coordenada1 = coordenada2 = distanciaE = "";
        }
        else {
            coordenada1 = "x= " + xA + ",   y= " + yA;
            if (xB == Integer.MIN_VALUE) {
                coordenada2 = distanciaE = "";
            }
            else {/*O cálculo para encontrar a distância euclidiana é simples 
                e pode ser encontrado em qualquer lugar na internet, no nosso caso, nos 
                baseamos nos ensinamentos da wikepédia, porém o código abaixo foi 
                inteiramente criado por nós*/
                coordenada2 = "x= " + xB + ",   y= " + yB;
                double BC = Math.pow(yB - yA, 2);//Obtém o quadrado
                double AC = Math.pow(xB - xA, 2);
                double distancia = Math.sqrt(BC + AC); //Obtém a raiz quadrada
                distanciaE = distancia + "";
                xA = xB = Integer.MIN_VALUE;
            }
        }
        interfaceI.exibeNovaDistanciaEuclidiana(coordenada1, coordenada2, distanciaE);
    }

    /**
     * Destaca os menores caminhos partindo de um vértice na interface gráfica
     *
     * @param caminhos Informação sobre os menores caminhos do vértices e suas
     * relações
     */
    private void destacaCaminhosInterface(LinkedList<Vertice> caminhos) {
        String conjuntoListas = ""; //Conteúdo da mensagem que será exibidas
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

    /**
     * Trilha um caminho que leva de um vértice a outro com base apenas em seus
     * antecessores
     *
     * @param verticeAtual Vértice cujos antecessores serão acessados para
     * formar um caminho
     * @param nCaminho Número do caminho criado
     * @return Retorna uma string contendo informações sobre o novo caminho
     * criado
     */
    private String destacaAntecessores(Vertice verticeAtual, int nCaminho) {
        String novoCaminho = verticeAtual == null || nCaminho == -1 ? "" : nCaminho + "º caminho: ";
        while (verticeAtual != null) {
            novoCaminho += verticeAtual.getNome();
            mxCell verticeEncontrado = listaVerticesMxCell.buscaVertice(verticeAtual.getNome());
            extendeCaminho(verticeEncontrado);
            if (verticeAtual.getVerticeAntecessor() != null) {
                novoCaminho += ", " + " ";
                extendeCaminho(listaArestasmxCell.buscaAresta(verticeAtual.getNome(), verticeAtual.getVerticeAntecessor().getNome()));
            }
            verticeAtual = verticeAtual.getVerticeAntecessor();
        }
        novoCaminho += "\n";
        interfaceI.insereSelecao(listaCaminhos.toArray());
        return novoCaminho;
    }

    /**
     * Estende a lista de caminhos que mais tarde serão destacados na interface
     * gráfica
     *
     * @param novaCelula Corresponde ao vértice
     */
    private void extendeCaminho(mxCell novaCelula) {
        if (!listaCaminhos.contains(novaCelula)) {
            listaCaminhos.add(novaCelula);
        }
    }

    /**
     * Obtém a informação sobre qual tipo de vértice o usuário selecionou para a
     * adição
     */
    public void defineItemSelecionado() {
        tipo_aparelho = interfaceI.getConteudoCombobox();
    }

    /**
     * Instancia um conjunto de objetos e métodos para fornecer uma lista de
     * menores caminhos ao sistema
     *
     * @param nomeVertice Nome do vértice que será o ponto de início (origem)
     * @param apenasTerminal Caso seja True, apenas vértices terminais
     * (computadores) serão aceitos, caso contrário, qualquer vértice é aceito
     * @return
     */
    private LinkedList<Vertice> obtemMenoresCaminhos(String nomeVertice, boolean apenasTerminal) {
        Dijkstra d = new Dijkstra();
        Vertice buscado = grafoDAO.buscaVertice(nomeVertice);
        LinkedList<Vertice> gerado = (!apenasTerminal || buscado.isTerminal()) ? d.obtemMenoresCaminhos(buscado, grafoDAO.getVertices()) : null;
        return gerado;
    }

    /**
     * Destaca e fornece o caminho entre dois vértices terminais
     *
     * @param caminhos Lista de caminhos de onde o caminho entre dois vértices
     * terminais será extraído
     */
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
                        /*Vale lembrar que o vértice atual sempre
                        será encontrado e selecionado depois que o vértice origem for encontrado
                        e ao mesmo tempo em que o vértice destino for encontrado*/
                        break;
                    }
                }
            }
        }
    }
}
