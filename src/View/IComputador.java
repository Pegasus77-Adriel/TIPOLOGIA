package View;

import com.mxgraph.view.mxGraph;

/**
 * Classe responsável por encaminhar uma imagem apropriada para representar um
 * computador
 *
 * @author Marcos Vinícius
 */
public class IComputador extends IDispositivos {

    /**
     * Instância a superclasse e lhe envia o dado da nova área de componentes do
     * grafo
     *
     * @param grafo Componente da interface gráfica usado para exibir graficamente o grafo
     */
    public IComputador(mxGraph grafo) {
        super(grafo);
    }

    /**
     * Informa caminho onde está contida a imagem correspondente ao computador
     * @return retorna o caminho da imagem onde está a imagem a ser usada pela superclasse
     */
    @Override
    public String caminhoImagem() {
        return "file:computador.gif";
    }

    /**
     * Informa o nome do estilo para os vértices que representarão o computador
     * @return Retorna o nome do estilo computador
     */
    @Override
    public String nomeEstilo() {
        return "computador";
    }

}
