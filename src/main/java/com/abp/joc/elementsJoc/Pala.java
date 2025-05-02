package com.abp.joc.elementsJoc;

import java.awt.*;

/**
 * Classe Pala que estén de la classe abstracta Elements Joc que
 * representa les pales del joc. La pala es pot moure verticalment
 * segons la direcció indicada pel jugador.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Pala extends ElementsJoc{
    /*Declaració d'atributs i variables*/
    private double xPercent, yPercent;

    // Velocitat de moviment de la pala
    private int velocitat = 5;

    // Percentatges de mida respecte a la pantalla
    private final double AMPL_PERCENT = 0.02; // 2% del ancho
    private final double ALT_PERCENT = 0.15;

    // Direcció del moviment de la pala
    private int direcio;

    /**
     * Constructor de la classe Pala.
     *
     * @param xPercent posició horitzontal
     * @param yPercent posició vertical
     */
    public Pala(double xPercent, double yPercent) {
        this.xPercent = xPercent;
        this.yPercent = yPercent;
    }

    /**
     * Calcula la posició i mida de la pala segons la mida de la pantalla.
     *
     * @param width amplada de la pantalla
     * @param height alçada de la pantalla
     */
    public void inicialitzarPosicio(int width, int height) {
        this.posX = (int) (xPercent * width);
        this.posY = (int) (yPercent * height);
        this.amplada = 5;
        this.altura = (int) (height*0.01);
    }

    /**
     * Mètode per dibuixar la pala a la pantalla.
     *
     * @param g context gràfic
     * @param width amplada actual de la pantalla
     * @param height alçada actual de la pantalla
     */
    @Override
    public void dibuixar(Graphics g, int width, int height) {
        g.setColor(new Color(179, 83, 191));
        amplada = (int) (width * AMPL_PERCENT);
        altura = (int) (height * ALT_PERCENT);

        posX = (int) (xPercent * width);
        posY = (int) (yPercent * height);
        g.fillRect(posX, posY, amplada, altura);
    }

    /**
     * Mètode per establir la direcció del moviment de la pala.
     * -1 = amunt, 1 = avall, 0 = aturada
     *
     * @param direcio direcció de moviment
     */
    public void setDirecio(int direcio){
        this.direcio = direcio;
    }

    /**
     * Mou la pala cap amunt o cap avall segons la direcció establerta.
     * Assegura que la pala no surti dels límits de la pantalla.
     *
     * @param width amplada de la pantalla
     * @param height alçada de la pantalla
     */
    @Override
    public void moviment(int width, int height) {
        altura = (int) (height * ALT_PERCENT);

        int novaPosY = posY + (direcio * velocitat);
        if (novaPosY >= 0 && novaPosY + altura <= height) {
            posY = novaPosY;
            yPercent = (double) posY / height;
        }
    }

    /**
     * Mètode per obtenir la posició de la pala en l'eix X.
     *
     * @param width
     * @return posició en l'eix X
     */
    public int getPosX(int width) {
        return (int) (xPercent * width);
    }

    /**
     * Mètode per obtenir la posició de la pala en l'eix Y.
     *
     * @param height
     * @return posició en l'eix Y
     */
    public int getPosY(int height) {
        return (int) (yPercent * height);
    }

    /**
     * Mètode per obtenir l'amplada de la pala
     *
     * @param width
     * @return amplada de la pala
     */
    public int getAmplada(int width) {
        return (int) (AMPL_PERCENT * width);
    }

    /**
     * Mètode per obetnir l'altura de la pala
     *
     * @param height
     * @return altura de la pala
     */
    public int getAltura(int height) {
        return (int) (ALT_PERCENT * height);
    }
}
