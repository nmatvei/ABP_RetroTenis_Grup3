package com.abp.elementsJoc;
import com.abp.Joc;
import com.abp.elementsJoc.Pala;


import javax.swing.*;
import java.awt.*;

/**
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Bola extends ElementsJoc{
    /*Declaració de variables i atributs*/
    private final static int DIMENSIO = 20;
    private int xa = 3;
    private int ya= 3;
    private int xaInicial;
    private int yaInicial;

    public Bola() {
        this.amplada = DIMENSIO;
        this.altura = DIMENSIO;
        this.posX = 300;
        this.posY = 200;
        this.xaInicial = xa;
        this.yaInicial = ya;
    }

    @Override
    public void dibuixar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(posX, posY, amplada, altura);
    }

    public void moviment(int width, int height, Pala pala1, Pala pala2, Joc joc, int nivellActual, List<Obstacle> obstacles) {
        // Fi de joc si surt per la dreta o l'esquerra
        if (posX + xa < 0 || posX + xa > width - amplada) {
            joc.gameOver();
        }

        // Col·lisió amb parets
        if (posY + ya < 0 || posY + ya > height - altura) ya = -ya;

        // Col·lisió amb pala esquerra
        if (posX <= pala1.getPosX() + pala1.getAmplada() &&
                posY + altura >= pala1.getPosY() &&
                posY <= pala1.getPosY() + pala1.getAltura()) {
            xa = Math.abs(xa); // Rebota cap a la dreta
        }

        // Col·lisió amb pala dreta
        if (posX + amplada >= pala2.getPosX() &&
                posY + altura >= pala2.getPosY() &&
                posY <= pala2.getPosY() + pala2.getAltura()) {
            xa = -Math.abs(xa); // Rebota cap a l'esquerra
        }

        posX += xa;
        posY += ya;
    }
    public void nivell(int nivellActual){
        int novaVelocitatX = xaInicial + (xaInicial * nivellActual)/10;
        int novaVelocitatY = yaInicial + (yaInicial * nivellActual)/10;

        xa = (xa > 0) ? novaVelocitatX : -novaVelocitatX;
        ya = (ya > 0) ? novaVelocitatY : -novaVelocitatY;
    }
}