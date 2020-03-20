package Observer;

import Controll.Controlador;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Classe responsável por chamar os métodos responsáveis pelo zoom no grafo
 *
 * @author Marcos Vinícius e Adriel
 */
public class ActionEventListenerRolagemMouse implements MouseWheelListener {

    Controlador padrao;

    /**
     * Inicializa uma instância de Controlador na qual poderá fazer chamadas
     * posteriores
     *
     * @param novo Controlador que terá seu(eus) método(os) acessado(os)
     */
    public ActionEventListenerRolagemMouse(Controlador novo) {
        this.padrao = novo;
    }

    /**
     * Realiza uma chamada ao método responsável por tratar as rolagens da roda
     * do mause, presente na classe Controlador
     *
     * @param mwe Arquivo de evento gerado a cada rolagem da roda do mouse
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        padrao.defineZoom(mwe.getWheelRotation(), mwe.isControlDown());
    }
}
