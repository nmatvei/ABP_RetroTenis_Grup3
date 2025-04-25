package com.abp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

import com.abp.elementsJoc.*;

public class Joc extends JPanel{
    private Pala pala1 = new Pala(0.1, 0.5);
    private Pala pala2 = new Pala(0.9, 0.5);
    private Bola bola = new Bola();
    private long tempsInici = System.currentTimeMillis();
    private long puntuacio = 0;
    private int nivell = 1;
    private long ultimCanviNivell = System.currentTimeMillis();


    public Joc() {
        this.setBackground(Color.white);
        this.setFocusable(true);
        LectorTeclat lectorTeclat = new LectorTeclat(pala1, pala2);
        this.addKeyListener(lectorTeclat);
    }
    public void moviment() {

        bola.moviment(getWidth(), getHeight(), pala1, pala2, this);
        pala1.moviment(getWidth(), getHeight());
        pala2.moviment(getWidth(), getHeight());

        long tempsActual = System.currentTimeMillis();

        if(tempsActual-ultimCanviNivell>=20000){
            nivell++;
            ultimCanviNivell=tempsActual;
            bola.nivell(nivell);
            repaint();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        pala1.dibuixar(g);
        pala2.dibuixar(g);
        bola.dibuixar(g);

        long tempsActual = System.currentTimeMillis();
        puntuacio = tempsActual - tempsInici;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Puntuació: " + puntuacio + " ms", 20, 30);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Nivell: " + nivell, 20, 60);

    }


    public void inicialitzarPosicions() {
        pala1.inicialitzarPosicio(getWidth(), getHeight());
        pala2.inicialitzarPosicio(getWidth(), getHeight());
    }



    public void gameOver() {
        long tempsFinal = System.currentTimeMillis();
        puntuacio = tempsFinal - tempsInici;
        JOptionPane.showMessageDialog(this, "Puntuacio: " + puntuacio, "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(ABORT);
    }

}
