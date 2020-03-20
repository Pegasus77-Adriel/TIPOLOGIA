/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import Controll.Controlador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Classe responsável por chamar os métodos responsáveis por tratar os cliques do mouse
 *
 * @author Marcos Vinícius e Adriel
 */
public class EventosMouse extends MouseAdapter {

    Controlador padrao;

    /**
     * Inicializa uma instância de Controlador na qual poderá fazer chamadas
     * posteriores
     *
     * @param novo Controlador que terá seu(eus) método(os) acessado(os)
     */
    public EventosMouse(Controlador novo) {
        this.padrao = novo;
    }

    /**
     * Realiza uma chamada ao método responsável por tratar os cliques do mause,
     * presente na classe Controlador
     *
     * @param clique Arquivo de evento gerado a cada clique do mouse
     */
    @Override
    public void mouseClicked(MouseEvent clique) {
        padrao.cliqueEsquerdo(clique.getX(), clique.getY());
    }

    /**
     * Realiza uma chamada ao método responsável por tratar os pressionamentos
     * do mause, presente na classe Controlador
     *
     * @param clique Arquivo de evento gerado a cada longo clique do mouse
     */
    @Override
    public void mousePressed(MouseEvent clique) {
        padrao.removeSelecao(clique.getX(), clique.getY());
    }
}
