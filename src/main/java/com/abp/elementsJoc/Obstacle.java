package com.abp.elementsJoc;

import java.awt.*;

public class Obstacle extends ElementsJoc {

    public Obstacle(int x, int y, int amplada, int altura){
        this.posX = x;
        this.posY = y;
        this.amplada = amplada;
        this.altura = altura;
    }



    @Override
    public void dibuixar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(posX, posY, amplada,altura);
    }


}
