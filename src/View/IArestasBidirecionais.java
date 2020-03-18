package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.Map;

/**
 * Classe responsável por definir a aparência das arestas na interface gráfica
 *
 * @author Marcos
 */
public class IArestasBidirecionais {

    /**
     * Prepara as configurações de estilo para as arestas sempre que uma nova
     * instância for chamada.
     *
     * @param grafo Componente de interface gráfica cujas arestas terão seu
     * estilo alterado
     */
    public IArestasBidirecionais(mxGraph grafo) {
        mxStylesheet stylesheet = grafo.getStylesheet();
        Map<String, Object> estiloPadrao = (Map<String, Object>) stylesheet.getDefaultEdgeStyle();
        estiloPadrao.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        estiloPadrao.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE);
        estiloPadrao.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE);
        estiloPadrao.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        estiloPadrao.put(mxConstants.STYLE_MOVABLE, false);
        estiloPadrao.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        estiloPadrao.put(mxConstants.STYLE_EDITABLE, false);

        stylesheet.setDefaultEdgeStyle(estiloPadrao);
    }
}
