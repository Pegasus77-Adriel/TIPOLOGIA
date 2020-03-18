package Observer;

import Control.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListenerSelecionaAparelho implements ActionListener {

    Controlador padrao;
    public ActionEventListenerSelecionaAparelho(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.defineItemSelecionado();
    }

}
