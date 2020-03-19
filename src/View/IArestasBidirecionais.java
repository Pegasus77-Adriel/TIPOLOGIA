package View;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.awt.Color;
import java.util.Map;

/**
 * Classe responsável por definir a aparência das arestas na interface gráfica
 *
 * @author Marcos Vinícius
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
        mxStylesheet stylesheet = grafo.getStylesheet(); //Pega a tabela de estilos do grafo
        Map<String, Object> estiloPadrao = (Map<String, Object>) stylesheet.getDefaultEdgeStyle(); //Pega o estilo padrão para as arestas
        estiloPadrao.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR); //Define que aresta agirá como um conector (é o padrão)
        estiloPadrao.put(mxConstants.STYLE_STARTARROW, mxConstants.NONE); //Retira a seta da ponta inicial da aresta
        estiloPadrao.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE); //Retira a seta da ponta final da aresta (é o padrão)
        estiloPadrao.put(mxConstants.STYLE_MOVABLE, false); //Impede que as arestas sejam movidas diretamente
        estiloPadrao.put(mxConstants.STYLE_EDITABLE, false); //Impede que as arestas tenham seu valor editado diretamente
        estiloPadrao.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_BOTTOM); //Determina que os pesos serão exibidos em baixo de cada aresta
        estiloPadrao.put(mxConstants.STYLE_FONTSIZE, 14); //Muda o tamanho da fonte do peso da aresta
        estiloPadrao.put(mxConstants.STYLE_FONTCOLOR, Color.BLACK); //Muda a cor da fonte do peso da aresta para preto
        
        stylesheet.setDefaultEdgeStyle(estiloPadrao); //Substitui o estilo padrão pelo estilo editado
    }
}
