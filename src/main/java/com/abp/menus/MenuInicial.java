package com.abp.menus;

import com.abp.eines.DadesJoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe MenuIncial que estén de la classe JPanel que representa un menú amb el logo del videojoc
 * i uns camps de text per introduir el nom d'usuari, en quin nivell es vol començar amb un menú per
 * seleccionar l'idioma en el qual es vol la partida.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class MenuInicial extends JPanel {

    // Constants d'idioma
    private final static String ESP = "Español";
    private final static String CAT = "Català";

    // Elements de la interfície gràfica
    private final JComboBox<String> OPCIONS_IDIOMES;
    private final JButton BOTO_START;
    private final JTextField INTRODUIR_NOM_JUGADOR;
    private final JTextField INTRODUIR_NIVELL;
    private final JLabel TEXT_INTRODUIR_NOM_JUGADOR;
    private final JLabel TEXT_SELECT_IDIOMA;
    private final JLabel TEXT_SELECT_NIVELL;
    private final JLabel COPYRIGHT;

    // Elements per recopilar dades i indicar l'inici de la partida
    private DadesJoc dj;
    private Runnable startListener; // Nou: listener per notificar quan es prem START

    /**
     * Constructor del menú inicial del joc. Crea i configura la interfície gràfica on
     * el jugador pot introduir el seu nom, seleccionar el nivell inicial i escollir l’idioma.
     *
     * @param dadesJoc objecte que conté la informació del jugador
     */
    public MenuInicial(DadesJoc dadesJoc) {
        this.dj = dadesJoc;

        // Configuració del panell
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Logo del joc
        ImageIcon logoOriginal = new ImageIcon("src/main/resources/photos/LogoRetroTennis.png");
        Image logoEscalat = logoOriginal.getImage().getScaledInstance(500, 198, Image.SCALE_SMOOTH);
        ImageIcon logoJocImatge = new ImageIcon(logoEscalat);
        JLabel logoJoc = new JLabel(logoJocImatge);
        logoJoc.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(logoJoc);

        // Camp per introduir el nom d'usuari
        TEXT_INTRODUIR_NOM_JUGADOR = new JLabel("Choose your nickname");
        estilText(TEXT_INTRODUIR_NOM_JUGADOR);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(TEXT_INTRODUIR_NOM_JUGADOR);

        INTRODUIR_NOM_JUGADOR = new JTextField();
        estilCampText(INTRODUIR_NOM_JUGADOR);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(INTRODUIR_NOM_JUGADOR);

        // Camp per introduir el nivell inicial
        TEXT_SELECT_NIVELL = new JLabel("Select the level you want to start");
        estilText(TEXT_SELECT_NIVELL);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(TEXT_SELECT_NIVELL);

        INTRODUIR_NIVELL = new JTextField();
        estilCampText(INTRODUIR_NIVELL);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(INTRODUIR_NIVELL);

        // Menu per seleccionar l'idioma
        TEXT_SELECT_IDIOMA = new JLabel("Select the language you want to play");
        estilText(TEXT_SELECT_IDIOMA);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(TEXT_SELECT_IDIOMA);

        OPCIONS_IDIOMES = new JComboBox<>();
        OPCIONS_IDIOMES.addItem(ESP);
        OPCIONS_IDIOMES.addItem(CAT);
        OPCIONS_IDIOMES.setMaximumSize(new Dimension(300, 35));
        OPCIONS_IDIOMES.setBackground(Color.decode("#f8d56c"));
        OPCIONS_IDIOMES.setFont(new Font("Calibri Bold", Font.PLAIN, 16));
        this.add(OPCIONS_IDIOMES);

        // Botó START
        BOTO_START = new JButton("START");
        BOTO_START.setAlignmentX(Component.CENTER_ALIGNMENT);
        BOTO_START.setBackground(Color.decode("#b353bf"));
        BOTO_START.setForeground(Color.WHITE);
        BOTO_START.setFont(new Font("Calibri Bold", Font.BOLD, 20));
        BOTO_START.setFocusPainted(false);
        BOTO_START.setMaximumSize(new Dimension(150, 40));

        BOTO_START.addActionListener(new ActionListener() {
            /**
             * Gestiona l'acció del botó START. Recull el nom d'usuari, el nivell inicial i l'idioma seleccionat
             * des del formulari, els desa a dj i notifica que s'ha de començar la partida.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Guardar dades del jugador
                String nomUsuari = INTRODUIR_NOM_JUGADOR.getText();
                dj.setNomUsuari(nomUsuari);

                int nivell;
                try {
                    nivell = Integer.parseInt(INTRODUIR_NIVELL.getText());
                } catch (Exception ex) {
                    nivell = 0;
                }
                dj.setNivell(nivell);

                String idiomaSeleccionat = (String) OPCIONS_IDIOMES.getSelectedItem();
                switch (idiomaSeleccionat) {
                    case ESP -> dj.setIdioma("esp");
                    case CAT -> dj.setIdioma("cat");
                    default -> System.out.println("Idioma no seleccionat");
                }

                // Notifiquem que s'ha de començar la partida
                if (startListener != null) startListener.run();
            }
        });

        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(BOTO_START);

        // Drets d'autor
        COPYRIGHT = new JLabel("©Group 3 (Pau, Nikita, Bea, Adria, Leyre)");
        COPYRIGHT.setForeground(Color.WHITE);
        COPYRIGHT.setFont(new Font("Calibri", Font.PLAIN, 14));
        COPYRIGHT.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 20)));
        this.add(COPYRIGHT);
    }

    /**
     * Mètode per configurar el listener que s'executa quan es prem el botó START.
     *
     * @param listener acció a executar
     */
    public void setStartListener(Runnable listener) {
        this.startListener = listener;
    }

    /**
     * Mètode per donar estil als camps de text que surten per pantalla
     *
     * @param camp el camp a modificar
     */
    private void estilCampText(JTextField camp) {
        camp.setMaximumSize(new Dimension(300, 35));
        camp.setBackground(Color.decode("#f8d56c"));
        camp.setForeground(Color.BLACK);
        camp.setFont(new Font("Calibri", Font.PLAIN, 14));
        camp.setHorizontalAlignment(JTextField.CENTER);
    }

    /**
     * Mètode per donar estil als petits títols que surten per pantalla.
     *
     * @param titol el títol a modificar
     */
    private void estilText(JLabel titol) {
        titol.setForeground(Color.WHITE);
        titol.setAlignmentX(Component.CENTER_ALIGNMENT);
        titol.setFont(new Font("Calibri Bold", Font.PLAIN, 16));
    }
}
