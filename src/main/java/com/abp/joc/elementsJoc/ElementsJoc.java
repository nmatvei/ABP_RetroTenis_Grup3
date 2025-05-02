package com.abp.joc.elementsJoc;

import java.awt.*;

/**
 * Classe abstracta ElementsJoc per definir els mètodes i atributs
 * comuns de tots els elements del joc (bola, pala i obstacle).
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 * @see Bola
 * @see Pala
 * @see Obstacle
 */
abstract public class ElementsJoc {

    // Atributs comuns dels elements del joc
    protected int amplada;
    protected int altura;
    protected int posX;
    protected int posY;

    /**
     * Mètode abstracte per definir el moviment d'un element del joc
     *
     * @param width
     * @param height
     */
    public void moviment(int width, int height) {}

    /**
     * Mètode per dibuixar un element del joc
     * @param g
     * @param width
     * @param height
     */
    public void dibuixar(Graphics g, int width, int height) {}
}