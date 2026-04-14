package org.mcr.lab2.display.clocks;

import org.mcr.lab2.chrono.ChronoSubject;

import javax.swing.*;
import java.awt.*;

public class Arab extends JPanel implements View {
    private static final String IMAGE_PATH = "cadran_chiffres_arabes.jpg";
    private final ChronoSubject chrono;
    private final Clock clock;

    public Arab(ChronoSubject chrono) {
        this.chrono = chrono;
        this.chrono.attach(this);
        this.clock = ClockFactory.getInstance().createClock(IMAGE_PATH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final long elapsedSeconds;
        if (chrono == null) {
            elapsedSeconds = 0;
        } else {
            elapsedSeconds = chrono.getElapsedTime();
        }
        clock.draw(this, g, elapsedSeconds, Color.BLUE, Color.BLACK, Color.RED);
        g.setColor(Color.DARK_GRAY);
        g.drawString("Chrono #" + chrono.getId(), getWidth() / 2 - 10, getHeight() / 2 + 30); //todo maybe add a number to the chrono like so
    }

    @Override
    public void update() {
        repaint();
    }
}