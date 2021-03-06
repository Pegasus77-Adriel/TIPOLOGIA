package Observer;

import Control.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsável por chamar os métodos responsáveis pela remoção de um
 * vértice no sistema
 *
 * @author Marcos Vinícius e Adriel 
 */
public class ActionEventListenerRemove implements ActionListener {

    Controlador padrao;

    /**
     * Inicializa uma instância de Controlador na qual poderá fazer chamadas
     * posteriores
     *
     * @param novo Controlador que terá seu(eus) método(os) acessado(os)
     */
    public ActionEventListenerRemove(Controlador novo) {
        this.padrao = novo;
    }

    /**
     * Inicia a chamada a um método de Controlador através de sua instância
     *
     * @param ae Corresponde ao evento causado pela do usuário
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.removeCelula();
    }
}
