package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.HashMap;

public class IArestasBidirecionais {

    public IArestasBidirecionais(mxGraph grafo) {
        mxStylesheet stylesheet = grafo.getStylesheet();
        String nomeEstilo = "ArestasBidirecionais";
        HashMap<String, Object> estilo = new HashMap();
        estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        estilo.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE);
        estilo.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE);
        estilo.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
        estilo.put(mxConstants.STYLE_MOVABLE, false);
        //estilo.put(mxConstants.STYLE_DASHED, true); (IGNORE)
        estilo.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        estilo.put(mxConstants.STYLE_EDITABLE, false);
        
        stylesheet.putCellStyle(nomeEstilo, estilo);
    }
}
