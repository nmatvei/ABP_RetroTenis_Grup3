package com.abp.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe encarregada de gestionar música que és un so repetitiu.
 * Utilitza la biblioteca javax.sound per carregar i reproduir un arxiu d'àudio en bucle.
 *
 * @author Pau, Nikita, Bea, Adria, Leyre
 */
public class Musica {

    // Element per guardar la música en un clip
    private Clip clip;

    /**
     * Constructor de la classe. Carrega el fitxer de so i el prepara per a la reproducció en bucle.
     */
    public Musica(String ruta) {
        try {
            File direcio = new File(ruta);

            //En cas de no trobar la ruta salta una excepció
            if (direcio == null) {
                throw new IllegalArgumentException("No trobat " + ruta);
            }

            // Si es troba, es carrega l'àudio en un clip i es reprodueix de manera indefinida
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(direcio);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode per iniciar la reproducció de la música si el clip ha estat
     * carregat correctament.
     */
    public void iniciaMusica(){
        if (clip != null){
            clip.start();
        }
    }

    /**
     * Mètode per aturar la reproducció de la música.
     */
    public void aturarMusica(){
        if (clip != null){
            clip.stop();
        }
    }

    /**
     * Mètode per ajustar el volum de la música.
     *
     * @param v el volum que es vol augmentar o disminuir
     */
    public void volum(float v) {
        if (clip != null) {
            // Control del volum del clip
            FloatControl volum = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volum.setValue(v);
        }
    }
}