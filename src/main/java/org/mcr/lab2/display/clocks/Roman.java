package org.mcr.lab2.display.clocks;

import javax.swing.*;
import java.awt.*;

public class Roman extends JPanel implements View {
    private static final String IMAGE_PATH = "cadran_chiffres_romains.jpg";
    // Chrono chrono
    Clock clock;
    Roman(/*Chrono chrono,*/){
        //this.chrono = chrono;
        //todo add this as listener for chrono
        this.clock = ClockFactory.getInstance().createClock(IMAGE_PATH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /**
        long elapsedSeconds = chrono.getElapsedTime();
        clock.draw(this, g, elapsedSeconds, Color.BLUE, Color.BLACK, Color.RED);
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("SansSerif", Font.PLAIN, 11));
         g.drawString("Chrono #" + chrono.number, getWidth() / 2 - 10, getHeight() / 2 + 30); //todo maybe add a number to the chrono like so
         */
    }

    public void toggle() {
        //chrono.toggle();
    }

    @Override
    public void update() {
        repaint();
    }
}
