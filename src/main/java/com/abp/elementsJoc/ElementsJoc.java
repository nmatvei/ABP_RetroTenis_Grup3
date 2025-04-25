package com.abp.elementsJoc;

import java.awt.*;

/**
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
abstract public class ElementsJoc {

    /*Declaració d'atributs*/
    protected int amplada;
    protected int altura;
    protected int posX;
    protected int posY;
    protected int ya=1;

    /**
     * Mètode abstracte per definir el moviment del objecte
     *
     * @param width
     * @param height
     */
    public void moviment(int width, int height) {

    }

    public void dibuixar(Graphics g) {}
}