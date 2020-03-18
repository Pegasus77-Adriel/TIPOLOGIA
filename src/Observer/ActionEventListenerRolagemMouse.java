package Observer;

import Control.Controlador;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ActionEventListenerRolagemMouse implements MouseWheelListener {
Controlador padrao;
    public ActionEventListenerRolagemMouse(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        padrao.defineZoom(mwe.getWheelRotation(), mwe.isControlDown());
    }
}
