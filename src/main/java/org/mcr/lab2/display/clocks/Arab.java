package org.mcr.lab2.display.clocks;

import org.mcr.lab2.chrono.ChronoSubject;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

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
        final long id;
        if (chrono == null) {
            elapsedSeconds = 0;
            id = 0;
        } else {
            elapsedSeconds = chrono.getElapsedTime();
            id = chrono.getId();
        }
        clock.draw(this, g, elapsedSeconds, Color.BLUE, Color.BLACK, Color.RED);
        g.setColor(Color.DARK_GRAY);

        // AI disclaimer: courtesy of ChatGPT regarding the text's horizontal centering
        String text = "Chrono #" + id;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.DARK_GRAY);

        FontMetrics fm = g2.getFontMetrics();

        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        // Proper centered position
        int x = (getWidth() - textWidth) / 2;

        int y = (getHeight() - textHeight) / 2 + fm.getAscent() + 30;

        g2.drawString(text, x, y);
    }

    @Override
    public void update() {
        repaint();
    }
}