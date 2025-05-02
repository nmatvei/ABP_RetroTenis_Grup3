package com.abp.menus;

import com.abp.eines.ConnexioDB;
import com.abp.eines.DadesJoc;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

/**
 * Classe MenuFinalPartida que estén de la classe que repr. Mostra un missatge quan
 * s'ha acabat la partida i una taula amb les 10 millors puntuacions. També conté dos
 * botons, un per tornar a jugar i un altre per sortir del joc.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class MenuFinalPartida extends JPanel {

    // Elements per conseguir i guardar dades
    private DadesJoc dj;
    private ConnexioDB db;

    // Elements de la interfície gràfica
    private final JLabel LOGO;
    private final ImageIcon ICONE;
    private final JPanel PANELL_SUPERIOR;
    private final JPanel PANELL_INFERIOR;
    private final JPanel PANELL_TAULA;
    private final JLabel PANELL_SCORE;
    private final JTable TAULA_JUGADORS;
    private final JButton retryButton;

    /**
     * Constructor per inicialitzar el menu al final d'una partida.
     *
     * @param dadesJoc per extreure les dades de les partides
     * @param Retry per tornar a executar el joc
     */
    public MenuFinalPartida(DadesJoc dadesJoc, Runnable Retry) {
        this.db = new ConnexioDB();
        this.dj = dadesJoc;
        String idioma = dj.getIdioma();

        // Configuració del panell
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // El logo de "Ben Jugat", que canvia depenent de l'idioma i es troba en el seu propi JPanel.
        if (dj.getIdioma().equals("esp")) {
            ICONE = new ImageIcon("src/main/resources/photos/wellPlayed_esp.png");
        } else {
            ICONE = new ImageIcon("src/main/resources/photos/wellPlayed_cat.png");
        }
        LOGO = new JLabel(ICONE);
        LOGO.setAlignmentX(Component.CENTER_ALIGNMENT);
        PANELL_SUPERIOR = new JPanel();
        PANELL_SUPERIOR.setBackground(Color.BLACK);
        PANELL_SUPERIOR.add(LOGO);
        add(PANELL_SUPERIOR, BorderLayout.NORTH);

        // Puntuació del jugador, que es troba en el seu JPanel i que es tradueix depenent de l'idioma seleccionat
        PANELL_TAULA = new JPanel();
        PANELL_TAULA.setLayout(new BoxLayout(PANELL_TAULA, BoxLayout.Y_AXIS));
        PANELL_TAULA.setBackground(Color.BLACK);
        PANELL_SCORE = new JLabel(db.obtenirTextTraduit(idioma, "scoreGameOver") + dj.getScore() + " p");
        PANELL_SCORE.setFont(new Font("Calibri Bold", Font.BOLD, 20));
        PANELL_SCORE.setForeground(Color.WHITE);
        PANELL_SCORE.setAlignmentX(Component.CENTER_ALIGNMENT);
        PANELL_TAULA.add(PANELL_SCORE);
        PANELL_TAULA.add(Box.createRigidArea(new Dimension(0, 10)));

        // Trucada al procediment de la base de dades per tal de saber el top 10 de jugadors amb millor puntuació
        String[] columnes = {db.obtenirTextTraduit(idioma, "topPlayers"), db.obtenirTextTraduit(idioma, "scorePlayers")};
        Object[][] dades = db.obtenirTop10Jugadors();

        // Taula on es veu el contingut del procediment hem trucat de la base de dades
        TAULA_JUGADORS = new JTable(dades, columnes);
        TAULA_JUGADORS.setEnabled(false);
        TAULA_JUGADORS.setFont(new Font("Calibri", Font.PLAIN, 15));
        TAULA_JUGADORS.setRowHeight(28);
        TAULA_JUGADORS.setForeground(Color.WHITE);
        TAULA_JUGADORS.setBackground(Color.BLACK);
        TAULA_JUGADORS.setGridColor(Color.GRAY);
        TAULA_JUGADORS.setFillsViewportHeight(true);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < TAULA_JUGADORS.getColumnCount(); i++) {
            TAULA_JUGADORS.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Capçaleres de la taula
        JTableHeader header = TAULA_JUGADORS.getTableHeader();
        header.setFont(new Font("Calibri Bold", Font.BOLD, 16));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(248, 213, 108));
        header.setReorderingAllowed(false);

        // Penal per contenir la taula
        JPanel taulaContainer = new JPanel();
        taulaContainer.setBackground(Color.BLACK);
        taulaContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Scroll panel per fer la taula més pètita
        JScrollPane scrollPane = new JScrollPane(TAULA_JUGADORS);
        scrollPane.setPreferredSize(new Dimension(500, 307));
        taulaContainer.add(scrollPane);

        // Afeguim la taula i indiquem que la volem en el centre
        PANELL_TAULA.add(taulaContainer);
        add(PANELL_TAULA, BorderLayout.CENTER);

        // Panel per contenir els botons per tornar a jugar i sortir
        PANELL_INFERIOR = new JPanel();
        PANELL_INFERIOR.setBackground(Color.BLACK);
        PANELL_INFERIOR.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20)); // Separació entre botons

        // Boto per tornar a jugar una partida
        retryButton = new JButton(db.obtenirTextTraduit(idioma, "try"));
        estilBotons(retryButton);
        retryButton.addActionListener(e -> {
            if (Retry != null) Retry.run();
        });

        // Boto per sortir del joc
        JButton exitButton = new JButton(db.obtenirTextTraduit(idioma, "exit"));
        estilBotons(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        // Afegim els botons i indiquem que el panel estigui a la part baixa de la finestra
        PANELL_INFERIOR.add(retryButton);
        PANELL_INFERIOR.add(exitButton);
        add(PANELL_INFERIOR, BorderLayout.SOUTH);
    }

    /**
     * Mètode que aplica un estil personalitzat a un botó (JButton).
     *
     * @param boto JButton al qual s'aplicarà l'estil personalitzat
     */
    private void estilBotons(JButton boto) {
        boto.setBackground(Color.decode("#b353bf"));
        boto.setForeground(Color.WHITE);
        boto.setFont(new Font("Calibri Bold", Font.BOLD, 15));
        boto.setFocusPainted(false);
        boto.setPreferredSize(new Dimension(150, 30));
    }
}