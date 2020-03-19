/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe responsável por chamar os métodos responsáveis pela saída do programa
 *
 * @author Marcos Vinícius
 */
public class ActionEventListenerSaida implements ActionListener {

    /**
     * Construtor da classe ActionEventListenerSaida
     *
     */
    public ActionEventListenerSaida() {
    }

    /**
     * Inicia a chamada a um método de Controlador através de sua instância
     *
     * @param ae Corresponde ao evento causado pela do usuário
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    }

}
