package com.abp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.abp.elementsJoc.*;

public class Joc extends JPanel{

    private Pala pala1 = new Pala(0.1, 0.5);
    private Pala pala2 = new Pala(0.9, 0.5);
    private Bola bola = new Bola();

    List<Obstacle> obstacles = new ArrayList<>();
    private boolean obstacleActiu = false;

    private long tempsInici = System.currentTimeMillis();
    private long puntuacio = 0;
    private int nivell = 1;
    private long ultimCanviNivell = System.currentTimeMillis();

    /**
     * Constructor del joc
     */
    public Joc() {
        this.setBackground(Color.white);
        this.setFocusable(true);
        LectorTeclat lectorTeclat = new LectorTeclat(pala1, pala2);
        this.addKeyListener(lectorTeclat);
    }


    public void moviment() {

        bola.moviment(getWidth(), getHeight(), pala1, pala2, this, nivell, obstacles);
        pala1.moviment(getWidth(), getHeight());
        pala2.moviment(getWidth(), getHeight());

        long tempsActual = System.currentTimeMillis();

        if(tempsActual - ultimCanviNivell >= 20000){
            nivell++;
            ultimCanviNivell = tempsActual;
            //bola.nivell(nivell);
            repaint();
        }

        if(!obstacleActiu && tempsActual- tempsInici >= 80000){
            obstacleActiu = true;
            generarObstacle();
        }


    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        //Dibuixem la pilota i les pales
        pala1.dibuixar(g);
        pala2.dibuixar(g);
        bola.dibuixar(g);

        //Es calcula la puntuacio a pari del tems actual i el inicial
        long tempsActual = System.currentTimeMillis();
        puntuacio = tempsActual - tempsInici;

        //Dibuxem els obstacles
        for (Obstacle obs : obstacles) {
            obs.dibuixar(g);
        }

        //Es dibuixa la puntuacio actual i el nivell
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntuació: " + puntuacio + " punt", 20, 30);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Nivell: " + nivell, 20, 60);

    }

    /**
     * Es determina la pocicio inicial de les pales
     */
    public void inicialitzarPosicions() {
        pala1.inicialitzarPosicio(getWidth(), getHeight());
        pala2.inicialitzarPosicio(getWidth(), getHeight());
    }

    /**
     * Metode que atura el joc quan es crida
     */
    public void gameOver() {
        long tempsFinal = System.currentTimeMillis();
        puntuacio = tempsFinal - tempsInici;
        JOptionPane.showMessageDialog(this, "Puntuacio: " + puntuacio, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }

    /**
     * Crea els obstacles de forma aleatoria
     */
    private void generarObstacle() {

        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            int x = 300 + rand.nextInt(getWidth() - 400);
            int y = 50 + rand.nextInt(getHeight() - 100);
            obstacles.add(new Obstacle(x, y, 20, 100));
        }
    }

}
