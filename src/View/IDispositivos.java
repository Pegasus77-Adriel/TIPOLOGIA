/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxImageBundle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Marcos
 */
public abstract class IDispositivos extends mxImageBundle {

    private mxGraph grafo;

    public IDispositivos(mxGraph grafo) {
        this.grafo = grafo;
        HashMap<String, Object> estilo = new HashMap();
        String caminhoImagem = this.caminhoImagem();
        String nomeEstilo = this.nomeEstilo();

        estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
        estilo.put(mxConstants.STYLE_RESIZABLE, false);
        estilo.put(mxConstants.STYLE_FONTSIZE, 14);
        estilo.put(mxConstants.STYLE_FONTCOLOR, Color.BLACK);
        estilo.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        estilo.put(mxConstants.STYLE_IMAGE, caminhoImagem);
        estilo.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        estilo.put(mxConstants.STYLE_EDITABLE, false);

        mxStylesheet stylesheet = grafo.getStylesheet();
        stylesheet.putCellStyle(nomeEstilo, estilo);
    }

    public abstract String caminhoImagem();

    public abstract String nomeEstilo();
}
