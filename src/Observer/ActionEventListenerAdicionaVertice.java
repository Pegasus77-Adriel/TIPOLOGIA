/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Control.Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Marcos
 */
public class ActionEventListenerAdicionaVertice implements ActionListener {
Controlador padrao;
    public ActionEventListenerAdicionaVertice(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        padrao.adicionaVertice();
    }

}
