package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.util.Map;

/**
 *
 * @author Marcos
 */
public class IInternet extends IDispositivos {

    /**
     * Classe responsável por encaminhar uma imagem apropriada para representar
     * a internet
     *
     * @param grafo
     */
    public IInternet(mxGraph grafo) {
        super(grafo);
        Map<String, Object> estiloInternet = grafo.getStylesheet().getStyles().get(this.nomeEstilo());
        estiloInternet.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        grafo.getStylesheet().putCellStyle(this.nomeEstilo(), estiloInternet);
    }

    /**
     *
     * @return
     */
    @Override
    public String caminhoImagem() {
        return "file:internet.GIF";
    }

    /**
     *
     * @return
     */
    @Override
    public String nomeEstilo() {
        return "Internet";
    }

}
