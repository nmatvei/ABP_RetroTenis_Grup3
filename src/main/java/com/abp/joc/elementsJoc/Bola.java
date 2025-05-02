package com.abp.joc.elementsJoc;

import com.abp.joc.Joc;
import com.abp.sound.So;

import java.util.List;

import java.awt.*;

/**
 * Classe Bola que estén de la classe abstracta ElementsJoc que representa una pilota.
 * Aquesta pilota es mou automàticament, pot rebotar contra parets, pales i obstacles.
 * Quan aquesta surt el costat dret o esquerre de la pantalla, acaba la partida.
 * També reprodueix un so quan rebota contra un objecte.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Bola extends ElementsJoc{

    /*Declaració de variables i atributs*/
    private final static double MIDA_PERCENT = 0.03;
    private final static int DIMENSIO = 20;

    //Velocitat de la bola
    private double xa = 3;
    private double ya = 3;

    //Velocitat inicial de la bola
    private double xaInicial;
    private double yaInicial;

    // Objecte per reproduir so
    private So so;

    /**
     * Constructor de Bola.
     */
    public Bola() {
        this.amplada = DIMENSIO;
        this.altura = DIMENSIO;
        this.posX=300;
        this.posY=200;
        this.xaInicial = xa;
        this.yaInicial = ya;
        this.so = new So("D:\\ABP\\ABP_RetroTenis_\\src\\main\\resources\\music\\ball_rebote.wav");
    }

    /**
     * Mètode per dibuixar la bola a la pantalla
     *
     * @param g context gràfic
     * @param width amplada de la finestra
     * @param height alçada de la finestra
     */
    @Override
    public void dibuixar(Graphics g, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        amplada = (int) (width * MIDA_PERCENT);
        altura = amplada; // La bola es rodona
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(posX, posY, amplada, altura);
    }

    /**
     * Mètode que conté totes les operacions necessàries perquè la pilota es mogui i
     * reaccioni en xocar amb els elements del Joc.
     *
     * @param width amplada de la finestra
     * @param height alçada de la finestra
     * @param pala1 primera pala
     * @param pala2 segona pala
     * @param joc objecte Joc per accedir al gameOver()
     * @param nivellActual nivell de dificultat actual
     * @param obstacles llista d'obstacles presents
     */
    public void moviment(int width, int height, Pala pala1, Pala pala2, Joc joc, int nivellActual, List<Obstacle> obstacles) {
        //Comprova per on ha sortit la bola
        if (posX + xa < 0 || posX + xa > width - amplada) {
            joc.gameOver();
        }
        //Rebota si toca el sostre o el terra
        if (posY + ya < 0 || posY + ya > height - altura) {
            ya = -ya;
            so.reproduir(); //So al tocar
        }

        // Comprovar col·lisió amb les pales
        colisioAmbPala(pala1, width, height);
        colisioAmbPala(pala2, width, height);

        //Velocitat depenent del nivell
        double novaVelocitatX = xaInicial + (xaInicial * nivellActual) / 10;
        double novaVelocitatY = yaInicial + (yaInicial * nivellActual) / 10;

        xa = (xa > 0) ? novaVelocitatX : -novaVelocitatX;
        ya = (ya > 0) ? novaVelocitatY : -novaVelocitatY;

        // Comprovar col·lisions amb obstacles
        colisioAmbObstacles(obstacles, width, height);

        //Actualitzar la posició
        posX += xa;
        posY += ya;
    }

    /**
     * Comprova col·lisió amb obstacles i aplica rebot si cal.
     *
     * @param obstacles llista d'obstacles
     * @param width amplada de la finestra
     * @param height alçada de la finestra
     */
    private void colisioAmbObstacles(List<Obstacle> obstacles, int width, int height){

        Rectangle bolaRect = new Rectangle(posX, posY, amplada, altura);

        for(Obstacle obstacle: obstacles){

            int obsX= obstacle.getPosX(width);
            int obsY= obstacle.getPosY(height);
            int obsW = obstacle.getAmplada(width);
            int obsH = obstacle.getAltura(height);

            Rectangle obsRect = new Rectangle(obsX, obsY, obsW, obsH);

            // En cas de que colisioni
            if(bolaRect.intersects(obsRect)){
                // Calcula el tipus de rebot
                boolean rebotVertical = posY + altura - ya <= obsY || posY - ya >= obsY + obsH;
                boolean rebotHoritzontal = posX + amplada - xa <= obsX || posX - xa >= obsX + obsW;

                if (rebotVertical) {
                    ya = -ya;
                } else if (rebotHoritzontal) {
                    xa = -xa;
                } else {
                    xa = -xa;
                    ya = -ya;
                }
                so.reproduir(); // So del rebot
            }

        }
    }

    /**
     * Comprova col·lisió amb una pala i calcula el rebot
     *
     * @param pala la pala amb la qual es comprova la col·lisió
     * @param width amplada de la finestra
     * @param height alçada de la finestra
     */
    private void colisioAmbPala(Pala pala, int width, int height){

        int palaX = pala.getPosX(width);
        int palaY = pala.getPosY(height);
        int palaW = pala.getAmplada(width);
        int palaH = pala.getAltura(height);

        if (posX + amplada >= palaX && posX <= palaX + palaW &&
                posY + altura >= palaY && posY <= palaY + palaH) {

            //Rebot horitzontal
            xa= (posX<width/2)? Math.abs(xa): -Math.abs(xa);
            //Si la
            if (posY > 0 && posY + altura < height) {

                //Rebot vertical
                int tocRelatiu = posY - palaY;

                if (tocRelatiu < palaH / 4) {
                    ya = -Math.abs(ya); //Rebot cap a dalt
                } else if (tocRelatiu > 2 * palaH / 4) {
                    ya = Math.abs(ya); // Rebot cap a baix
                }

            }
            so.reproduir(); // So del rebot
        }
    }
}
