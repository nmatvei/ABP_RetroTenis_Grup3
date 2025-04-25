package com.abp;

import com.abp.elementsJoc.Pala;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe LectorTeclat que implementa la interfície KeyListener per tal de poder realitzar accions dins del joc quan una
 * tecla sigui premsada
 */
public class LectorTeclat implements KeyListener{
    /*Declaració d'atributs*/
    private Pala pala1, pala2;

    /**
     * Constructor de la classe
     *
     * @param pala1
     * @param pala2
     */
    public LectorTeclat(Pala pala1, Pala pala2) {
        this.pala1 = pala1;
        this.pala2 = pala2;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Métode
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> pala1.setDirecio(-1);
            case KeyEvent.VK_S  -> pala1.setDirecio(1);
            case KeyEvent.VK_UP  -> pala2.setDirecio(-1);
            case KeyEvent.VK_DOWN  -> pala2.setDirecio(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S -> pala1.setDirecio(0);
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> pala2.setDirecio(0);
        }
    }
}