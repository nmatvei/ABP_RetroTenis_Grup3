package com.abp.elementsJoc;

import javax.swing.*;
import java.awt.*;

public class MenuInicial extends JPanel {

    public MenuInicial(){
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }

    private void incialitzacio(){
        JLabel titol = new JLabel("<html><h1>RETRO TENNIS</h1></html>");
        JLabel subtitol = new JLabel("<html><h1>Resistance Game</h1></html>");
    }
}
