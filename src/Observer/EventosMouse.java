/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Control.Controlador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventosMouse extends MouseAdapter {

    Controlador padrao;

    public EventosMouse(Controlador novo) {
        this.padrao = novo;
    }

    @Override
    public void mouseClicked(MouseEvent clique) {
        padrao.cliqueEsquerdo(clique.getX(), clique.getY());
    }

    @Override
    public void mousePressed(MouseEvent clique) {
        padrao.removeSelecao(clique.getX(), clique.getY());
    }
}
