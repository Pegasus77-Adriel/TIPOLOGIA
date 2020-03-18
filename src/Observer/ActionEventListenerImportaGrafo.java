package Observer;

import Control.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListenerImportaGrafo implements ActionListener {
Controlador padrao;
    public ActionEventListenerImportaGrafo(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.importaConfiguracoes();
    }

}
