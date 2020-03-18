package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.util.Map;

/**
 *
 * @author Marcos Vinícius
 */
public class IInternet extends IDispositivos {

    /**
     * Classe responsável por encaminhar uma imagem apropriada para representar
     * a internet
     *
     * @param grafo Componente da interface gráfica usado para exibir
     * graficamente o grafo
     */
    public IInternet(mxGraph grafo) {
        super(grafo);
        Map<String, Object> estiloInternet = grafo.getStylesheet().getStyles().get(this.nomeEstilo());
        estiloInternet.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        grafo.getStylesheet().putCellStyle(this.nomeEstilo(), estiloInternet);
    }

    /**
     * Informa caminho onde está contida a imagem correspondente a internet
     *
     * @return retorna o caminho onde estará a imagem a ser usada pela
     * superclasse
     */
    @Override
    public String caminhoImagem() {
        return "file:internet.GIF";
    }

    /**
     * Informa o nome do estilo para os vértices que representarão a internet
     *
     * @return Retorna o nome do estilo Internet
     */
    @Override
    public String nomeEstilo() {
        return "Internet";
    }

}
