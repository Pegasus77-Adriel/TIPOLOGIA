/* Edited by Diego Pedro Gonçalves da Silva
 * 
 * 
 */
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

public class InterfacePrincipal extends JFrame {

    private final String extensao_padrao = "txt";
    private final String estilo_aresta = "ArestasBidirecionais";
    private final String texto_distancia = "Distância euclidiana: ";
    private final String texto_coordenada1 = "Coordenada 1: ";
    private final String texto_coordenada2 = "Coordenada 2: ";
    private final int posicao_X = 300;
    private final int posicao_Y = 50;

    private mxGraph grafo;
    private mxGraphComponent areaCompGrafo;
    private JTextField caixaTexto;
    private JButton botaoAdd;
    private JButton botaoDel;
    private JButton botaoLigar;

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
    private JComboBox selecaoAparelho;

    public InterfacePrincipal(ActionListener eventoRemover, ActionListener eventoSair,
            ActionListener eventoExportar, ActionListener eventoImportar, ActionListener eventoLigar,
            ActionListener eventoItem, ActionListener eventoAdicionar, MouseListener eventoCliqueMouse, MouseWheelListener eventoRodaMouse) {
        super("WillFall Tipologias - Versão 1.7"); //Título da janela principal
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

    public final void iniciaGUI() {
        Dimension informacoesTela = Toolkit.getDefaultToolkit().getScreenSize();
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

        areaCompGrafo.setConnectable(false);
        areaCompGrafo.setSwimlaneSelectionEnabled(true);
        areaCompGrafo.setSwimlaneSelectionEnabled(true);
        areaCompGrafo.setFocusable(false);

        areaCompGrafo.setBackground(Color.WHITE);
        areaCompGrafo.getViewport().setOpaque(false);

        defineAcoesMouse();
        this.setLocationRelativeTo(null); //Posiciona a janela no centro da tela
        this.setVisible(true);
    }

    public mxGraph getPainel() {
        return this.grafo;
    }

    public JTextField getCaixatexto() {
        return this.caixaTexto;
    }

    public mxGraphComponent getAreaComponentes() {
        return this.areaCompGrafo;
    }

    public String recebeNomeVertice() {
        String nomeVertice = JOptionPane.showInputDialog("Digite o nome do vértice: ");
        return nomeVertice == null ? "" : nomeVertice;
    }

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

    public void exibeMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public String selecionaDiretorioAbertura() {
        FileNameExtensionFilter extensoesPermitidas = new FileNameExtensionFilter(extensao_padrao, extensao_padrao);
        JFileChooser arquivo = new JFileChooser();
        arquivo.setFileFilter(extensoesPermitidas);
        arquivo.setDialogTitle("Escolha o arquivo que será importado:");
        arquivo.showOpenDialog(null);
        return arquivo.getSelectedFile() == null ? null : arquivo.getSelectedFile().getAbsolutePath();
    }

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

    private JPanel painelLinha2() {
        JPanel painel2 = new JPanel();
        painel2.setLayout(new BoxLayout(painel2, BoxLayout.X_AXIS));
        this.distanciaEuclidiana = new JLabel(texto_distancia);
        painel2.add(this.distanciaEuclidiana);
        return painel2;
    }

    private JPanel painelLinha3() {
        JPanel painel3 = new JPanel();
        painel3.setLayout(new BoxLayout(painel3, BoxLayout.X_AXIS));

        JLabel selecioneItem = new JLabel("Selecione um item:     ");
        painel3.add(selecioneItem);

        String[] aparelhos = {"computador", "internet", "rooteador"};
        selecaoAparelho = new JComboBox(aparelhos);
        selecaoAparelho.setSelectedIndex(0);
        selecaoAparelho.addActionListener(eventoItem);

        painel3.add(selecaoAparelho);

        botaoAdd = new JButton("Adicionar");
        painel3.add(botaoAdd);
        botaoAdd.addActionListener(eventoAdicionar);

        return painel3;
    }

    public void exibeNovaDistanciaEuclidiana(String coordenada1, String coordenada2, String novaDistancia) {
        String espaco = "                  ";
        this.distanciaEuclidiana.setText(texto_coordenada1 + coordenada1 + espaco + texto_coordenada2 + coordenada2 + espaco + texto_distancia + novaDistancia);
    }

    private void defineAcoesMouse() {
        areaCompGrafo.getGraphControl().addMouseListener(eventoCliqueMouse);
        areaCompGrafo.addMouseWheelListener(eventoRodaMouse);
    }

    private void defineIcone() {
        ImageIcon novoIcone = new ImageIcon("icone.png");
        this.setIconImage(novoIcone.getImage());
    }

    public String getConteudoCombobox() {
        return (String) selecaoAparelho.getSelectedItem();
    }

    public mxCell adicionaVertice(String nomeVertice, int larguraVertice, int alturaVertice, String tipoVertice) {
        grafo.getModel().beginUpdate();
        mxCell novoVertice = (mxCell) grafo.insertVertex(grafo.getDefaultParent(), nomeVertice, nomeVertice, this.posicao_X, this.posicao_Y, larguraVertice, alturaVertice, tipoVertice);
        reorganizaLayout();
        grafo.getModel().endUpdate();
        return novoVertice;
    }

    public mxCell adicionaAresta(String pesoAresta, mxCell vertice1, mxCell vertice2, boolean visibilidade) {
        grafo.getModel().beginUpdate();
        mxCell novaAresta = (mxCell) grafo.insertEdge(grafo.getDefaultParent(), pesoAresta, pesoAresta, vertice1, vertice2, estilo_aresta);
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

    public mxCell removeCelula(mxICell celula) {
        grafo.getModel().beginUpdate();
        mxCell removida = (mxCell) grafo.getModel().remove(celula);
        grafo.getModel().endUpdate();
        return removida;
    }
}
