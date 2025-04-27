package com.abp;

import javax.swing.*;

/**
 * Classe Finestra que conté un JFrame on es truquen les clàsses que representen les parts del joc com el menú inicial,
 * el joc en si i el menú després d'acabar la pàrtida.
 */
public class Finestra extends JPanel{

    /*Declaració de variables */
    private final static int AMPLADA_FINESTRA = 1000;
    private final static int ALTURA_FINESTRA = 600;

    /**
     * Métode per tal d'incialitzar la finestra on s'executa el joc.
     * @throws InterruptedException
     */
    public void inici() throws InterruptedException {

        /*Instànciem un JFrame amb el títol de la finestra*/
        JFrame frame = new JFrame("Mini Tennis");
        Joc panel = new Joc();

        frame.setSize(AMPLADA_FINESTRA, ALTURA_FINESTRA);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setVisible(true);

        panel.inicialitzarPosicions();
        panel.requestFocusInWindow();


        while (true) {
            panel.moviment();
            panel.repaint();
            Thread.sleep(10);
        }

    }

    /**
     * Mètode per conseguir l'altura de la finestra
     *
     * @return altura de la finestra
     */
    public int getAlturaFinestra() {
        return ALTURA_FINESTRA;
    }

    /**
     * Mètode per conseguir l'amplada de la finestra
     *
     * @return l'amplada de la finestra
     */
    public int getAmpladaFinestra() {
        return AMPLADA_FINESTRA;
    }
}