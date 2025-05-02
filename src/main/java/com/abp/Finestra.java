package com.abp;

import com.abp.joc.Joc;
import com.abp.sound.Musica;
import com.abp.menus.MenuFinalPartida;
import com.abp.menus.MenuInicial;
import com.abp.eines.ConnexioDB;
import com.abp.eines.DadesJoc;

import javax.swing.*;

/**
 * Classe Finestra que conté un JFrame on es truquen les diferents parts del joc:
 * Menú inicial, el joc i el menú en perdre una partida. També es truca a la
 * connexió amb la base de dades i la reproducció de música.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Finestra {

    // Component per crear una finestra i anar guardant dades de la partida.
    private final static int AMPLADA_FINESTRA = 1000;
    private final static int ALTURA_FINESTRA = 600;
    private final JFrame finestra;
    private final DadesJoc dadesJoc;

    /**
     * Constructor de la classe Finestra. Inicialitza el JFrame i les dades del joc.
     */
    public Finestra() {
        // Inicialització del JFrame
        finestra = new JFrame("Retro Tennis: Endurance Game");
        finestra.setSize(AMPLADA_FINESTRA, ALTURA_FINESTRA);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setLocationRelativeTo(null);
        finestra.setResizable(false);
        dadesJoc = new DadesJoc();
    }

    /**
     * Mètode principal per iniciar el programa.
     * Mostra el menú inicial, i quan es dona a START s'inicia.
     */
    public void executarPrograma() {
        // Crear el menú inicial
        MenuInicial menuInicial = new MenuInicial(dadesJoc);
        finestra.setContentPane(menuInicial);
        finestra.setVisible(true);

        // Definir què passa quan es prem START
        menuInicial.setStartListener(() -> {
            iniciarJoc();
        });
    }

    /**
     * Carrega el panell del joc i comença la lògica.
     */
    private void iniciarJoc() {

        // Crear el panell del joc
        Joc joc = new Joc(dadesJoc);
        joc.inicialitzarPosicions();

        //Salta el menu final en cas de GameOver
        joc.setGameOver(this::partidaFinalitzada);

        // Reemplaçar el contingut del JFrame
        finestra.setContentPane(joc);
        finestra.revalidate();
        finestra.repaint();

        // Música de fons
        Musica music = new Musica("D:\\ABP\\ABP_RetroTenis_\\src\\main\\resources\\music\\8bits.wav");
        music.iniciaMusica();
        music.volum(-5.0f);

        // Focalitzar i començar bucle del joc
        joc.requestFocusInWindow();

        // Creem un nou fil d'execució pel joc, i anem repintant la pantalla per representar el moviment de la pilota.
        new Thread(() -> {
            try {
                while (joc.isAtura()) {
                    joc.moviment();
                    joc.repaint();
                    Thread.sleep(10);
                }
                // Aturem la música de fons
                music.aturarMusica();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Mètode que es crida quan la partida finalitza. Registra les dades de la partida
     * a la base de i mostra el menú final de la partidaba a la finestra principal.
     */
    private void partidaFinalitzada(){

        // Reproduïm la música al finalitzar la partida i reduïm el volum
        Musica music = new Musica("D:\\ABP\\ABP_RetroTenis_\\src\\main\\resources\\music\\8bits.wav");
        music.iniciaMusica();
        music.volum(-5.0f);

        // Obrim connexió amb la base de dades i resgitrem la partida
        ConnexioDB connexio = new ConnexioDB();
        connexio.insertarRegistrePartida(dadesJoc.getNomUsuari(), dadesJoc.getScore(), dadesJoc.getIdioma());

        // Mostrem el menú final
        MenuFinalPartida mf = new MenuFinalPartida(dadesJoc, this::iniciarJoc);
        finestra.setContentPane(mf);
        finestra.revalidate();
        finestra.repaint();

        // Aturem la música
        music.aturarMusica();
    }

    /**
     * Retorna l'alçada de la finestra del joc.
     *
     * @return altura en píxels
     */
    public int getAlturaFinestra() {
        return ALTURA_FINESTRA;
    }

    /**
     * Retorna l'amplada de la finestra del joc.
     *
     * @return amplada en píxels
     */
    public int getAmpladaFinestra() {
        return AMPLADA_FINESTRA;
    }
}
