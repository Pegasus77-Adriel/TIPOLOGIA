/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos
 */
public class ArestaTest {
    
   
    

    /**
     * Teste de retornar peso da Aresta.
     */
    @Test
    public void testGetPeso() {
        System.out.println("getPeso");
        Vertice vertice = new Vertice("A",true);
        Aresta instance = new Aresta(3,vertice);
        int expResult = 3;
        int result = instance.getPeso();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Teste de retornar Vertice final da aresta.
     */
    @Test
    public void testGetFim() {
        System.out.println("getFim");
        Vertice vertice = new Vertice("A",true);
        Aresta instance = new Aresta(3,vertice);
        Vertice expResult = vertice;
        Vertice result = instance.getFim();
        assertEquals(expResult, result);
        
    }
    
}
