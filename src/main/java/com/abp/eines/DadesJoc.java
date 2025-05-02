package com.abp.eines;

/**
 * Classe DadesJoc que ens permet emmagatzemar tota la informació que
 * rebem al menu inicial i al joc, per tal de després poder fer el registre
 * de la partida i traduïr tots els textos del joc i del menú final.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class DadesJoc {

    // Atributs on es guarden les dades joc
    private String idioma;
    private String nomUsuari;
    private int nivell;
    private int score;

    /**
     * Mètode per saber quin és l'idioma de la partida
     *
     * @return idioma de la partida
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * Mètode per saber quin és el nom de l'usuari de la partida
     *
     * @return nom del jugador
     */
    public String getNomUsuari() {
        return nomUsuari;
    }

    /**
     * Mètode per saber quin és el nivell en el qual es vol començar
     *
     * @return nivell en el qual es comença
     */
    public int getNivell() {
        return nivell;
    }

    /**
     * Mètode per saber quina és la puntuació de la partida.
     *
     * @return puntuació de la partida
     */
    public int getScore() {
        return score;
    }

    /**
     * Mètode per canviar l'idioma de la partida
     *
     * @param idioma
     */
    public void setIdioma (String idioma){
        this.idioma = idioma;
    }

    /**
     * Mètode per canviar el nivell de la partida
     *
     * @param nivell
     */
    public void setNivell(int nivell) {
        this.nivell = nivell;
    }

    /**
     * Mètode per canviar el nom de l'usuari
     *
     * @param nomUsuari
     */
    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    /**
     * Mètode per canviar la puntuació de la partida
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }
}