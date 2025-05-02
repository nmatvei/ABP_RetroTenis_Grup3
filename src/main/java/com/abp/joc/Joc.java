package com.abp.joc;

import com.abp.eines.ConnexioDB;
import com.abp.joc.elementsJoc.Bola;
import com.abp.joc.elementsJoc.Obstacle;
import com.abp.joc.elementsJoc.Pala;
import com.abp.sound.So;
import com.abp.eines.DadesJoc;
import com.abp.eines.LectorTeclat;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe Joc que esten de la classe JPanel que representa el panel de joc on l'usuari juga.
 * Aquesta classe conté tots els elements del joc (pales, boles i obstacles) i va comptabilitzant
 * la puntuació, canviant l'escenari depenent d'aquesta.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Joc extends JPanel {
    private Runnable GameOver;
    private boolean atura = true;

    // Elements gràfics del joc
    private Pala pala1 = new Pala(0.1, 0.5);
    private Pala pala2 = new Pala(0.9, 0.5);
    private Bola bola = new Bola();
    List<Obstacle> obstacles = new ArrayList<>();
    private String textPuntuacio;
    private String textNivell;

    // Elements per anar controlant els temps per iniciar la partida, el canvi de nivell i la generació d'obstacles
    private long tempsInici = System.currentTimeMillis();
    private long ultimCanviNivell = System.currentTimeMillis();
    private long tempsUltimObstacle;

    // Elements per guardar les dades
    private DadesJoc dj;
    private int puntuacio = 0;
    private int nivell;

    /**
     * Constructor del joc. Dona un fons al joc, configura el nivell en el qual
     * es comença la partida i afegim un "listener" per moure les pales.
     *
     * @param dadesJoc
     */
    public Joc(DadesJoc dadesJoc) {
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.dj = dadesJoc;
        nivell= dj.getNivell();
        LectorTeclat lectorTeclat = new LectorTeclat(pala1, pala2);
        this.addKeyListener(lectorTeclat);
        ConnexioDB connexioDB = new ConnexioDB();
        this.textPuntuacio = connexioDB.obtenirTextTraduit(dj.getIdioma(),"score" );
        this.textNivell = connexioDB.obtenirTextTraduit(dj.getIdioma(), "level");
    }

    /**
     * Mètode que crida tot lo relacionat amb el moviment dels elements del joc
     */
    public void moviment() {

        int width = getWidth();
        int height = getHeight();

        //Crida els metodes per moure la bola i les pales
        bola.moviment(width, height, pala1, pala2, this, nivell, obstacles);
        pala1.moviment(width, height);
        pala2.moviment(width, height);

        long tempsActual = System.currentTimeMillis();

        if (tempsActual - ultimCanviNivell >= 20000) {
            nivell++;
            ultimCanviNivell = tempsActual;
            repaint();
        }

        if (obstacles.size() < 15 && nivell >= 3 &&
                tempsActual - tempsUltimObstacle >= 20000) {
            generarObstacle();
            tempsUltimObstacle = tempsActual;
        }
    }


    /**
     * Metode que pinta els elements del joc a la pantalla
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Dibuixem la pilota i les pales
        pala1.dibuixar(g, width, height);
        pala2.dibuixar(g, width, height);
        bola.dibuixar(g, width, height);

        // Es calcula la puntuacio a pari del tems actual i el inicial
        long tempsActual = System.currentTimeMillis();
        puntuacio = (int) (tempsActual - tempsInici);

        // Dibuxem els obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.dibuixar(g, width, height);
        }

        // Es dibuixa la puntuacio actual i el nivell
        ConnexioDB connexioDB = new ConnexioDB();
        g.setColor(new Color(248, 213, 108));
        g.setFont(new Font("Calibri Bold", Font.PLAIN, 20));
        g.drawString(textPuntuacio + puntuacio + "p", getWidth() / 2 - 80, 30);
        g.drawString(textNivell + nivell, getWidth() / 2 - 40, 60);
    }

    /**
     * Mètode per determinar la posició inicial de les pales
     */
    public void inicialitzarPosicions() {
        pala1.inicialitzarPosicio(getWidth(), getHeight());
        pala2.inicialitzarPosicio(getWidth(), getHeight());
    }

    /**
     * Mètode per crear els obstacles de forma aleatòria
     */
    private void generarObstacle() {
        Random rand = new Random();

        // Entre 20% i 80% d'amplada
        double xPercent = 0.2 + rand.nextDouble() * 0.6;
        // Entre 10% i 90% d'alçada
        double yPercent = 0.1 + rand.nextDouble() * 0.8;

        obstacles.add(new Obstacle(xPercent, yPercent));
    }

    /**
     * Mètode per aturar el joc en perdre i reproduir un so
     */
    public void gameOver() {
        long tempsFinal = System.currentTimeMillis();
        puntuacio = (int) (tempsFinal - tempsInici);
        dj.setScore(puntuacio);
        So soOver = new So("D:\\ABP\\ABP_RetroTenis_\\src\\main\\resources\\music\\game_over.wav");
        soOver.reproduir();

        atura = false;

        if (GameOver != null) {
            SwingUtilities.invokeLater(GameOver);
        }
    }

    /**
     * Mètode per configurar el GameOver
     *
     * @param GameOver
     */
    public void setGameOver(Runnable GameOver) {
        this.GameOver = GameOver;
    }

    /**
     * Mètode per saber si s'ha aturat el joc
     *
     * @return si s'ha aturat
     */
    public boolean isAtura(){
        return atura;
    }
}