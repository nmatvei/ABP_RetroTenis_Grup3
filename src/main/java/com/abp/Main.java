package com.abp;

/**
 * Classe Main on es crida a la finestra per a començar a executar
 * l'aplicació
 *
 * @author  Pau, Nikita, Bea, Adria, Leyre
 */
public class Main {
    public static void main(String[] args){

        // Instànciem un objecte de la classe Finestra
        Finestra programa = new Finestra();

        try {
            // Inetentem executar el joc
            programa.executarPrograma();
        }
        catch (Exception e) {
            // Si no ho podem, llençem una RuntimeException
            throw new RuntimeException(e);
        }
    }
}