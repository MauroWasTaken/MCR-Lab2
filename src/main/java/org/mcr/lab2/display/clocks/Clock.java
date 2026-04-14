package org.mcr.lab2.display.clocks;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Clock {

    private BufferedImage image;

    Clock(String imagePath){
        if (!imagePath.isBlank()) {
            try { image = ImageIO.read(new File("src/main/resources/clockfaces/%s".formatted(imagePath)).getAbsoluteFile()); }
            catch (Exception e) { System.err.println("Could not load image: " + imagePath); }
        }
    }

    public void draw(JPanel panel, Graphics g, long elapsedSeconds,
                     Color hourColor, Color minuteColor, Color secondColor) {
        // AI Disclamer: this function was given to us by claude, couldn't figure out how to make the hands of the clock

        int w = panel.getWidth(), h = panel.getHeight();
        int cx = w / 2, cy = h / 2;
        int radius = (int)(Math.min(w, h) / 2.0 * 0.85);

        // Draw background image
        if (image != null) g.drawImage(image, 0, 0, w, h, null);

        // Compute angles (0° = 12 o'clock, clockwise)
        double secs = elapsedSeconds % 60;
        double mins = (elapsedSeconds / 60.0) % 60;
        double hours = (elapsedSeconds / 3600.0) % 12;

        drawHand(g, cx, cy, Math.toRadians(hours * 30 - 90),  (int)(radius * 0.50), 5, hourColor);
        drawHand(g, cx, cy, Math.toRadians(mins  *  6 - 90),  (int)(radius * 0.70), 3, minuteColor);
        drawHand(g, cx, cy, Math.toRadians(secs  *  6 - 90),  (int)(radius * 0.80), 2, secondColor);

        // Centre dot
        g.setColor(Color.DARK_GRAY);
        g.fillOval(cx - 5, cy - 5, 10, 10);
    }

    private void drawHand(Graphics g, int cx, int cy, double angle, int length, int stroke, Color color) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        // Small tail on the opposite side (20% of hand length)
        int tail = (int)(length * 0.2);
        g2.drawLine(
                cx - (int)(Math.cos(angle) * tail), cy - (int)(Math.sin(angle) * tail),
                cx + (int)(Math.cos(angle) * length), cy + (int)(Math.sin(angle) * length)
        );
    }
}
