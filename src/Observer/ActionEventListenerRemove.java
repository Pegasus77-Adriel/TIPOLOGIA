package Observer;

import Control.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListenerRemove implements ActionListener {
    Controlador padrao;
    public ActionEventListenerRemove(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.removeCelula();
    }
}
