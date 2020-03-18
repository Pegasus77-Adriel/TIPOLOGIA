package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxImageBundle;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;
import java.util.HashMap;

/**
 * SuperClasse responsável por substituir as imagens dos vértices padrão por imagens personalizadas
 * @author Marcos Vinícius
 */
public abstract class IDispositivos extends mxImageBundle {

    private mxGraph grafo;

    /**
     * Cria um novo estilo personalizado à mesma medida que for instânciado por suas subclasses
     * @param grafo Componente da interface gráfica que terá seus estilos de vértices editados
     */
    public IDispositivos(mxGraph grafo) {
        this.grafo = grafo;
        HashMap<String, Object> estilo = new HashMap();
        String caminhoImagem = this.caminhoImagem(); //O método das classesfilhas devem fornecer a informaçao
        String nomeEstilo = this.nomeEstilo(); //O método das classesfilhas devem fornecer a informaçao

        estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE); //Define que o vértice agora será como uma imagem
        estilo.put(mxConstants.STYLE_RESIZABLE, false); //Impede o vértice de ser redimensionado
        estilo.put(mxConstants.STYLE_FONTSIZE, 14); //Muda o tamanho da fonte do nome do vértice
        estilo.put(mxConstants.STYLE_FONTCOLOR, Color.BLACK); //Muda o estilo da fonte do vértice
        estilo.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD); //
        estilo.put(mxConstants.STYLE_IMAGE, caminhoImagem);
        estilo.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
        estilo.put(mxConstants.STYLE_EDITABLE, false);

        mxStylesheet stylesheet = grafo.getStylesheet();
        stylesheet.putCellStyle(nomeEstilo, estilo);
    }

    /**
     *
     * @return
     */
    public abstract String caminhoImagem();

    /**
     *
     * @return
     */
    public abstract String nomeEstilo();
}
