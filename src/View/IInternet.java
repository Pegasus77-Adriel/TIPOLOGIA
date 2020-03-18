package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import java.util.Map;

public class IInternet extends IDispositivos {

    public IInternet(mxGraph grafo) {
        super(grafo);
        Map<String, Object> estiloInternet = grafo.getStylesheet().getStyles().get(this.nomeEstilo());
        estiloInternet.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_SHADOW);
        grafo.getStylesheet().putCellStyle(this.nomeEstilo(), estiloInternet);
    }

    @Override
    public String caminhoImagem() {
        return "file:internet.GIF";
    }

    @Override
    public String nomeEstilo() {
        return "internet";
    }

}
