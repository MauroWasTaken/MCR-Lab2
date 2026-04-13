package org.mcr.lab2.display.clocks;

import javax.swing.*;
import java.awt.*;

public class Arab extends JPanel implements View {
    private static final String IMAGE_PATH = "cadran_chiffres_arabes.jpg";
    // Chrono chrono
    Clock clock;
    Arab(/*Chrono chrono,*/){
        //this.chrono = chrono;
        //todo add this as listener for chrono
        this.clock = ClockFactory.getInstance().createClock(IMAGE_PATH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /**
         long elapsedSeconds = chrono.getElapsedTime();
         clock.draw(this, g, elapsedSeconds, Color.DARK_GRAY, Color.BLACK, Color.YELLOW);
         g.setColor(Color.DARK_GRAY);
         g.drawString("Chrono #" + chrono.number, getWidth() / 2 - 10, getHeight() / 2 + 30); //todo maybe add a number to the chrono like so
         /** */
    }

    public void toggle() {
        //chrono.toggle();
    }

    @Override
    public void update() {
        repaint();
    }
}