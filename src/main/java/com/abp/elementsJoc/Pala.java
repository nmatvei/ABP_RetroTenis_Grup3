package com.abp.elementsJoc;

import java.awt.*;

/**
 * Classe Pala que estén de la classe
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Pala extends ElementsJoc{
    /*Declaració d'atributs i variables*/
    private double xPercent, yPercent;
    private int velocitat = 5;
    private int direcio;

    /**
     * Constructor
     * @param xPercent
     * @param yPercent
     */
    public Pala(double xPercent, double yPercent) {
        this.amplada = 10;
        this.altura = 60;
        this.xPercent = xPercent;
        this.yPercent = yPercent;
    }

    public void inicialitzarPosicio(int width, int height) {
        this.posX = (int) (xPercent * width);
        this.posY = (int) (yPercent * height);
    }

    @Override
    public void dibuixar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(posX, posY, amplada, altura);
    }



    @Override
    public void moviment(int width, int height) {
        int novaPosY = posY + (direcio * velocitat);

        if (novaPosY >= 0 && novaPosY + altura <= height) {
            posY = novaPosY;
        }

    }

    public void setDirecio(int direcio){
        this.direcio = direcio;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getAmplada() {
        return amplada;
    }

    public int getAltura() {
        return altura;
    }



}
