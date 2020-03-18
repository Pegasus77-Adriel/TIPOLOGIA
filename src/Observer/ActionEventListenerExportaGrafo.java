package Observer;

import Control.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEventListenerExportaGrafo implements ActionListener {
Controlador padrao;
    public ActionEventListenerExportaGrafo(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.exportaConfiguracoes();
    }

}
