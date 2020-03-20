package Observer;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 *
 * @author Marcos e Adriel
 */
public class WindowFocusListenerPerdeFocoJanela implements WindowFocusListener {

    JFrame padrao;

    /**
     * Método de adicionar nova janela
     * @param novaJanela Nova janela 
     */
    public WindowFocusListenerPerdeFocoJanela(JFrame novaJanela) {
        padrao = novaJanela;
    }

    /**
     * Método de ganhar foco da janela
     * @param we O evento
     */
    @Override
    public void windowGainedFocus(WindowEvent we) {
    }

    /**
     * Método de perder o foco da janela
     * @param we O evento
     */
    @Override
    public void windowLostFocus(WindowEvent we) {
        padrao.dispose();
    }

}
