package View;

import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe responsável pela interface gráfica e pelos componentes visuais do
 * grafo
 *
 * @author Marcos Vinícius
 */
public class InterfacePrincipal extends JFrame {

    private final String extensao_padrao = "txt";
    private final String texto_distancia = "Distância euclidiana: ";
    private final String texto_coordenada1 = "Coordenada 1: ";
    private final String texto_coordenada2 = "Coordenada 2: ";
    private final int posicao_X = 300; //Posição onde será inserido um vértice
    private final int posicao_Y = 50;  //Posição onde será inserido um vértice

    private mxGraph grafo; //Componente responsável por gerênciar a parte gráfica que representa o grafo
    private mxGraphComponent areaCompGrafo; //Área da parte gráfica destinada aos componentes
    private JTextField caixaTexto;
    private JButton botaoAdd;
    private JButton botaoDel;
    private JButton botaoLigar;
    private JComboBox selecaoAparelho;
    private JLabel distanciaEuclidiana;

    //Lista de eventos pré-programados:
    private MouseListener eventoCliqueMouse;
    private MouseWheelListener eventoRodaMouse;
    private ActionListener eventoRemover;
    private ActionListener eventoSair;
    private ActionListener eventoExportar;
    private ActionListener eventoImportar;
    private ActionListener eventoLigar;
    private ActionListener eventoItem;
    private ActionListener eventoAdicionar;

    /**
     * Instancia os recursos necessários para a apresentação gráfica e
     * inicializa as variáveis responsáveis pelos eventos da interface
     *
     * @param eventoRemover Evento que inicia o processo de remoção de uma
     * célula
     * @param eventoSair Evento que inicia o processo de saída do programa
     * @param eventoExportar Evento que inicia o processo de exportação de dados
     * @param eventoImportar Evento que inicia o processo de importação de dados
     * @param eventoLigar Evento que inicia o processo de inserção de arestas
     * @param eventoItem Evento que fornece informações sobre o tipo de vértice
     * que o usuário deseja adicionar
     * @param eventoAdicionar Evento que inicia o processo de inserção de
     * vértice
     * @param eventoCliqueMouse Evento que pode iniciar outros eventos com base
     * nas ações do mouse
     * @param eventoRodaMouse Evento que inicia o processo de zoom
     */
    public InterfacePrincipal(ActionListener eventoRemover, ActionListener eventoSair,
            ActionListener eventoExportar, ActionListener eventoImportar, ActionListener eventoLigar,
            ActionListener eventoItem, ActionListener eventoAdicionar, MouseListener eventoCliqueMouse, MouseWheelListener eventoRodaMouse) {

        super("WillFall Tipologias - Versão 1.8"); //Título da janela principal
        this.defineIcone();

        this.eventoAdicionar = eventoAdicionar;
        this.eventoCliqueMouse = eventoCliqueMouse;
        this.eventoExportar = eventoExportar;
        this.eventoImportar = eventoImportar;
        this.eventoItem = eventoItem;
        this.eventoLigar = eventoLigar;
        this.eventoRemover = eventoRemover;
        this.eventoRodaMouse = eventoRodaMouse;
        this.eventoSair = eventoSair;

        this.iniciaGUI();
        //Inicializando os componentes gráficos correspondentes aos vértices:
        new IComputador(this.grafo);
        new IRooteador(this.grafo);
        new IInternet(this.grafo);
        new IArestasBidirecionais(this.grafo);
    }

    /**
     * Inicializa a maior parte dos componentes gráficos e os exibe ao usuário
     */
    public final void iniciaGUI() {
        Dimension informacoesTela = Toolkit.getDefaultToolkit().getScreenSize(); //Permite saber a resolução da tela
        int largura = (int) informacoesTela.getWidth();
        int altura = (int) informacoesTela.getHeight();
        setSize(largura, altura); //Define a resolução da janela

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        grafo = new mxGraph(); //Cria um novo painel
        areaCompGrafo = new mxGraphComponent(grafo); //Cria a área onde ficarão os componentes do grafo
        Dimension dimensao = new Dimension((int) (largura * 0.90), (int) (altura * 0.75)); //Define a dimensão para a área dos componentes do grafo
        areaCompGrafo.setPreferredSize(dimensao);
        this.getContentPane().add(areaCompGrafo); //Adicina a área de componentes do grafo ao painel da janela principal
        areaCompGrafo.setImportEnabled(true); //Permite a importação de imagens para o sistema (talvez seja desnecessário futuramente mas coloquei por precaução)

        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS)); //Altera o layout (organização dos componentes)
        this.add(painelLinha1());
        this.add(painelLinha2());
        this.add(painelLinha3());
        this.pack(); //Compacta os espaços entre os componentes da janela para otimizar o espaço

        areaCompGrafo.setConnectable(false); //Impede a ligação de vértices através do mouse
        areaCompGrafo.setDragEnabled(false); //Altera o modo de arrastar pelo mouse
        areaCompGrafo.setBackground(Color.WHITE); //Define a cor branca para o fundo do grafo
        areaCompGrafo.getViewport().setOpaque(false); //Impede que o fundo do grafo seja transparente

        defineAcoesMouse();
        this.setLocationRelativeTo(null); //Posiciona a janela no centro da tela
        this.setVisible(true); //Torna a janela visível
    }

    /**
     * Fornece o acesso ao componente gráfico responsável por gerenciar os
     * elementos gráficos de um grafo (vértices e arestas)
     *
     * @return Retorna o componente grafico de gerenciamento do grafo
     */
    public mxGraph getPainel() {
        return this.grafo;
    }

    /**
     * Permite o acesso à área onde os elementos gráficos do grafo serão
     * posicionados
     *
     * @return Retorna a área onde os elementos gráficos estarão posicionados
     */
    public mxGraphComponent getAreaComponentes() {
        return this.areaCompGrafo;
    }

    /**
     * Permite que o usuário insira o nome de um vértice que ele deseja
     * adicionar ao sistema
     *
     * @return Retorna o nome do novo vértice que acaba de ser inserido pelo
     * usuário ou retorna null caso o usuário cancele a seleção
     */
    public String recebeNomeVertice() {
        String nomeVertice = JOptionPane.showInputDialog("Digite o nome do vértice: ");
        return nomeVertice == null ? "" : nomeVertice;
    }

    /**
     * Permite que o usuário insira informações sobre o a aresta que ele deseja
     * adicionar ao sistema
     *
     * @return Retorna um vetor contendo as informações da aresta em questão
     */
    public String[] recebeInformacoesLigacao() {
        String nomeVertice1 = JOptionPane.showInputDialog("Digite o nome do primeiro vertice: ");
        String nomeVertice2 = JOptionPane.showInputDialog("Digite o nome do segundo vertice: ");
        String nomeAresta = JOptionPane.showInputDialog("Peso da ligação: ");
        String[] informacoes = new String[3];
        informacoes[0] = nomeVertice1 == null ? "" : nomeVertice1;
        informacoes[1] = nomeVertice2 == null ? "" : nomeVertice2;
        informacoes[2] = nomeAresta == null ? "" : nomeAresta;
        return informacoes;
    }

    /**
     * Permite que o usuário escolha um diretório para salvamento do arquivo de
     * configuração
     *
     * @return Retorna o caminho para onde deverá ser criado o arquivo de
     * configuração ou retorna null caso o usuário cancele a seleção
     */
    public String selecionaDiretorioSalvamento() {
        FileNameExtensionFilter extensoesPermitidas = new FileNameExtensionFilter(extensao_padrao, extensao_padrao);
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileFilter(extensoesPermitidas);
        arquivo.setDialogTitle("Escolha o local onde salvar o arquivo:");
        int arquivoSalvo = arquivo.showSaveDialog(null);
        if (arquivoSalvo == JFileChooser.APPROVE_OPTION) {
            return arquivo.getSelectedFile().getAbsolutePath() + "." + extensao_padrao;
        }
        return null;
    }

    /**
     * Permite que uma mensagem informativa seja exibida ao usuário
     *
     * @param mensagem Conteúdo que será exibido pela mensagem
     */
    public void exibeMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    /**
     * Permite que o usuário selecione o diretório de um arquivo de configuração
     * (em .txt)
     *
     * @return retorna o caminho do arquivo de texto de configuração
     */
    public String selecionaDiretorioAbertura() {
        FileNameExtensionFilter extensoesPermitidas = new FileNameExtensionFilter(extensao_padrao, extensao_padrao);
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileFilter(extensoesPermitidas);
        arquivo.setDialogTitle("Escolha o arquivo que será importado:");
        arquivo.showOpenDialog(null);
        return arquivo.getSelectedFile() == null ? null : arquivo.getSelectedFile().getAbsolutePath();
    }

    /**
     * Cria um novo painel onde estarão arranjados alguns componentes como
     * botões, caixas de texto, etc.
     *
     * @return Retorna o painel criado
     */
    private JPanel painelLinha1() {
        JPanel painel1 = new JPanel();
        painel1.setLayout(new BoxLayout(painel1, BoxLayout.X_AXIS));

        botaoDel = new JButton("Remover");
        painel1.add(botaoDel);
        botaoDel.addActionListener(eventoRemover);

        JButton botaoExit = new JButton("Sair");
        painel1.add(botaoExit);
        botaoExit.addActionListener(eventoSair);

        JButton exportaConfig = new JButton("Exportar configurações");
        painel1.add(exportaConfig);
        exportaConfig.addActionListener(eventoExportar);

        JButton importaConfig = new JButton("Importar configurações");
        painel1.add(importaConfig);
        importaConfig.addActionListener(eventoImportar);

        botaoLigar = new JButton("Ligar 2 vértices");
        painel1.add(botaoLigar);
        botaoLigar.addActionListener(eventoLigar);

        return painel1;
    }

    /**
     * Cria um novo painel onde estarão arranjados alguns componentes como
     * botões, caixas de texto, etc.
     *
     * @return Retorna o painel criado
     */
    private JPanel painelLinha2() {
        JPanel painel2 = new JPanel();
        painel2.setLayout(new BoxLayout(painel2, BoxLayout.X_AXIS));
        this.distanciaEuclidiana = new JLabel(texto_distancia);
        painel2.add(this.distanciaEuclidiana);
        return painel2;
    }

    /**
     * Cria um novo painel onde estarão arranjados alguns componentes como
     * botões, caixas de texto, etc.
     *
     * @return Retorna o painel criado
     */
    private JPanel painelLinha3() {
        JPanel painel3 = new JPanel();
        painel3.setLayout(new BoxLayout(painel3, BoxLayout.X_AXIS));

        JLabel selecioneItem = new JLabel("Selecione um item:     ");
        painel3.add(selecioneItem);

        String[] aparelhos = {"computador", "Internet", "rooteador"};
        selecaoAparelho = new JComboBox(aparelhos);
        selecaoAparelho.setSelectedIndex(0);
        selecaoAparelho.addActionListener(eventoItem);

        painel3.add(selecaoAparelho);

        botaoAdd = new JButton("Adicionar");
        painel3.add(botaoAdd);
        botaoAdd.addActionListener(eventoAdicionar);

        return painel3;
    }

    /**
     * Exibe, para o usuário, informações sobre a distância euclidiana entre dois pontos da janela em que ele clicou consecutivamente
     * @param coordenada1 Informações sobre a coordenada do primeiro ponto 
     * @param coordenada2 Informações sobre a coordenada do segundo ponto
     * @param novaDistancia Distância euclidianada entre os dois pontos selecionados
     */
    public void exibeNovaDistanciaEuclidiana(String coordenada1, String coordenada2, String novaDistancia) {
        String espaco = "                  ";
        this.distanciaEuclidiana.setText(texto_coordenada1 + coordenada1 + espaco + texto_coordenada2 + coordenada2 + espaco + texto_distancia + novaDistancia);
    }

    /**
     * Define quais serão os eventos do mouse
     */
    private void defineAcoesMouse() {
        areaCompGrafo.getGraphControl().addMouseListener(eventoCliqueMouse);
        areaCompGrafo.addMouseWheelListener(eventoRodaMouse);
    }

    /**
     * Define um ícone para o programa
     */
    private void defineIcone() {
        ImageIcon novoIcone = new ImageIcon("icone.png");
        this.setIconImage(novoIcone.getImage());
    }

    /**
     * Fornece o conteúdo oculto em uma combobox
     * @return Retorna "computador", "rooteador" ou "Internet"
     */
    public String getConteudoCombobox() {
        return (String) selecaoAparelho.getSelectedItem();
    }

    /**
     * Adiciona um novo vértice na interface gráfica
     * @param nomeVertice Nome do novo vértice
     * @param larguraVertice Largura do novo vértice
     * @param alturaVertice Altura do novo vértice 
     * @param tipoVertice
     * @return
     */
    public mxCell adicionaVertice(String nomeVertice, int larguraVertice, int alturaVertice, String tipoVertice) {
        grafo.getModel().beginUpdate();
        mxCell novoVertice = (mxCell) grafo.insertVertex(grafo.getDefaultParent(), nomeVertice, nomeVertice, this.posicao_X, this.posicao_Y, larguraVertice, alturaVertice, tipoVertice);
        reorganizaLayout();
        grafo.getModel().endUpdate();
        return novoVertice;
    }

    /**
     *
     * @param pesoAresta
     * @param vertice1
     * @param vertice2
     * @param visibilidade
     * @return
     */
    public mxCell adicionaAresta(String pesoAresta, mxCell vertice1, mxCell vertice2, boolean visibilidade) {
        grafo.getModel().beginUpdate();
        mxCell novaAresta = (mxCell) grafo.insertEdge(grafo.getDefaultParent(), pesoAresta, pesoAresta, vertice1, vertice2);
        novaAresta.setVisible(visibilidade);
        reorganizaLayout();
        grafo.getModel().endUpdate();
        return novaAresta;
    }

    private void reorganizaLayout() {
        mxFastOrganicLayout novaOrganizacao = new mxFastOrganicLayout(grafo);
        novaOrganizacao.setMinDistanceLimit(0.02);
        novaOrganizacao.setForceConstant(150);
        novaOrganizacao.setUseInputOrigin(true);
        novaOrganizacao.execute(grafo.getDefaultParent());
    }

    /**
     *
     * @param celula
     * @return
     */
    public mxCell removeCelula(mxICell celula) {
        grafo.getModel().beginUpdate();
        mxCell removida = (mxCell) grafo.getModel().remove(celula);
        grafo.getModel().endUpdate();
        return removida;
    }
}
