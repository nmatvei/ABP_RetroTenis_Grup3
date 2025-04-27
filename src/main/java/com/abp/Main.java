package com.abp;

public class Main {
    public static void main(String[] args) {

        Finestra ventana = new Finestra();

        try {

            ventana.inici();

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }
    }
}