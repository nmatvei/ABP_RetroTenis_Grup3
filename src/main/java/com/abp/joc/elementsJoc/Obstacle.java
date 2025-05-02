package com.abp.joc.elementsJoc;

import java.awt.*;

/**
 * Classe Obstacle que representa un obstacle dins del joc. Els obstacles són elements
 * rectangulars que es dibuixen a la pantalla i interactuen amb la bola.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 * @see Bola
 */
public class Obstacle extends ElementsJoc {

    // Atribust i variables constants
    private double xPercent, yPercent;
    private final double AMPL_PERCENT = 0.02; // 2% de l'amplada
    private final double ALT_PERCENT = 0.15; // 1,5% de l'altura

    /**
     * Constructor d'Obstacle
     *
     * @param xPercent
     * @param yPercent
     */
    public Obstacle(double xPercent, double yPercent){
        this.xPercent = xPercent;
        this.yPercent = yPercent;
    }

    /**
     * Metode que especifica que s'ha de dibuixar a la pantalla un rectangle vermell.
     * Calcula la mida i posició del rectangle a partir del percentatge.
     *
     * @param g context gràfic per dibuixar
     * @param width amplada actual de la pantalla
     * @param height alçada actual de la pantalla
     *
     */
    @Override
    public void dibuixar(Graphics g, int width, int height) {
        this.posX = (int)(xPercent * width);
        this.posY = (int)(yPercent * height);
        this.amplada = (int)(AMPL_PERCENT * width);
        this.altura = (int)(ALT_PERCENT * height);
        g.setColor(Color.RED);
        g.fillRect(posX, posY, amplada,altura);
    }

    /**
     * Mètode per obtenir la posició de l'obstacle en l'eix X.
     *
     * @param width
     * @return posició en l'eix X
     */
    public int getPosX(int width) {
        return (int) (xPercent * width);
    }

    /**
     * Mètode per obtenir la posició de l'obstacle en l'eix Y.
     *
     * @param height
     * @return posició en l'eix Y
     */
    public int getPosY(int height) {
        return (int) (yPercent * height);
    }

    /**
     * Mètode per obtenir l'amplada de l'obstacle
     *
     * @param width
     * @return amplada de l'obstacle
     */
    public int getAmplada(int width) {
        return (int) (AMPL_PERCENT * width);
    }

    /**
     * Mètode per obtenir l'altura de l'obstacle
     *
     * @param height
     * @return altura de l'obstacle
     */
    public int getAltura(int height) {
        return (int) (ALT_PERCENT * height);
    }
}