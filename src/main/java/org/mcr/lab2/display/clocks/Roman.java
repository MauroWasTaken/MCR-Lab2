package org.mcr.lab2.display.clocks;

import org.mcr.lab2.chrono.ChronoSubject;

import javax.swing.*;
import java.awt.*;

public class Roman extends JPanel implements View {
    private static final String IMAGE_PATH = "cadran_chiffres_romains.jpg";
    private final ChronoSubject chrono;
    private final Clock clock;

    public Roman(ChronoSubject chrono) {
        this.chrono = chrono;
        this.chrono.attach(this);
        this.clock = ClockFactory.getInstance().createClock(IMAGE_PATH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        final long elapsedSeconds = chrono.getElapsedTime();
        clock.draw(this, g, elapsedSeconds, Color.DARK_GRAY, Color.BLACK, Color.ORANGE);
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("SansSerif", Font.PLAIN, 11));
        g.drawString("Chrono #" + chrono.getId(), getWidth() / 2 - 10, getHeight() / 2 + 30); //todo maybe add a number to the chrono like so
    }

    @Override
    public void update() {

        repaint();
    }
}
