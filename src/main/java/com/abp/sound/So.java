package com.abp.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Clase So que s'encarega de tot el so (un so que es reprodueix només un cop)
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class So {

    // Elements per guardar sons en clips
    private Clip clip;

    /**
     * Constructor de So, que agafa el directori donat i comprova si existeix, si existeix,
     * agafa el so i el guarda en AudioInputStream per després guardar-ho a Clip, un cop fet
     * si es crida el mètode reproduir, reprodueix el so.
     *
     * @param ruta la ruta del fitxer de l'àudio
     */
    public So(String ruta) {
        try {
            File direcio = new File(ruta);

            //En cas de no trobar la ruta salta una excepció
            if (direcio == null) {
                throw new IllegalArgumentException("No trobat " + ruta);
            }

            // Si es troba, es carrega l'àudio en un clip
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(direcio);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode per reproduir el sol carregat
     */
    public void reproduir() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0); // Reiniciem el so
            clip.start();
        }
    }
}
