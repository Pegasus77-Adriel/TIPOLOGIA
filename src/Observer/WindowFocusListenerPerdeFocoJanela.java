package Observer;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JFrame;

/**
 *
 * @author Marcos
 */
public class WindowFocusListenerPerdeFocoJanela implements WindowFocusListener {

    JFrame padrao;

    public WindowFocusListenerPerdeFocoJanela(JFrame novaJanela) {
        padrao = novaJanela;
    }

    @Override
    public void windowGainedFocus(WindowEvent we) {
    }

    @Override
    public void windowLostFocus(WindowEvent we) {
        padrao.dispose();
    }

}
