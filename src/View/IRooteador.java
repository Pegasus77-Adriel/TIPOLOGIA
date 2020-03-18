package View;

import com.mxgraph.view.mxGraph;

/**
 *
 * @author Marcos Vinícius
 */
public class IRooteador extends IDispositivos {

    /**
     * Classe responsável por encaminhar uma imagem apropriada para representar
     * o rooteador
     *
     * @param grafo Componente da interface gráfica usado para exibir
     * graficamente o grafo
     */
    public IRooteador(mxGraph grafo) {
        super(grafo);
    }

    /**
     * Informa caminho onde está contida a imagem correspondente ao rooteador
     *
     * @return retorna o caminho onde estará a imagem a ser usada pela
     * superclasse
     */
    @Override
    public String caminhoImagem() {
        return "file:rooteador.gif";
    }

    /**
     * Informa o nome do estilo para os vértices que representarão o rooteador
     * @return Retorna o nome do estilo rooteador
     */
    @Override
    public String nomeEstilo() {
        return "rooteador";
    }

}
