package com.abp.eines;

import com.abp.joc.elementsJoc.Pala;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Classe LectorTeclat que implementa la interfície KeyListener per tal de poder realitzar accions dins del joc quan una
 * tecla sigui premsada o deixi d'estar-ho.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 * @see Pala
 */
public class LectorTeclat implements KeyListener{
    /*Declaració d'atributs*/
    private Pala pala1, pala2;

    /**
     * Constructor de la classe, que rep com a parametres les pales del joc.
     *
     * @param pala1
     * @param pala2
     */
    public LectorTeclat(Pala pala1, Pala pala2) {
        this.pala1 = pala1;
        this.pala2 = pala2;
    }

    /**
     * Mètode per executar alguna cosa quan una tecla sigui teclejada. Cada tecla teclejada
     * s'interpreta com un esdeveniment, i segons quina tecla sigui, es farà una acció una altra.
     *
     * @param e l'esdeveniment a processar que és la tecla teclejada.
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Mètode per determinar el moviment de les pales depenent de les tecles premsades.
     * Cada tecla premsada es compta com un esdeveniment, per tant, depenent de l'esdeveniment
     * a processar, es mourà una pala o una altra.
     *
     * La pala de l'esquerra (pala1) canvia la seva direcció amb les tecles W i S.
     * La pala de la dreta (pala2) canvia la seva direcció amb les tecles Up i Down (fletxes).
     *
     * @param e l'esdeveniment a processar que és la tecla premsada.
     * @see Pala
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

    /**
     * Mètode per detenir les pales si les tecles han deixat d'estar premsades. Cada tecla
     * que no sigui premsada es compta com un esdeveniment, per tant, depenent de l'esdeveniment
     * a processar, es detindrà una pala o una altra.
     *
     * La pala de l'esquerra (pala1) es deté amb les tecles W i S.
     * La pala de la dreta (pala2) es deté amb les tecles Up i Down (fletxes).
     *
     * @param e l'esdeveniment a processar que és la tecla premsada
     * @see Pala
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_S -> pala1.setDirecio(0);
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> pala2.setDirecio(0);
        }
    }
}