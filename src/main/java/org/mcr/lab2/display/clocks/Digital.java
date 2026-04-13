package org.mcr.lab2.display.clocks;

import javax.swing.*;
import java.awt.*;

public class Digital extends JPanel implements View {
    JPanel panel;
    // Chrono chrono
    Clock clock;
    private final JLabel label;

    Digital(/*Chrono chrono,*/){
        this.panel = panel;
        //this.chrono = chrono;
        //todo add this as listener for chrono

        label = new JLabel("", SwingConstants.CENTER);

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    public void toggle() {
        //chrono.toggle();
    }

    @Override
    public void update() {
        // elapsedSeconds = chrono.getElapsedTime();

        //long h = elapsedSeconds / 3600;
        //long m = (elapsedSeconds % 3600) / 60;
        //long s = elapsedSeconds % 60;

        //String time = String.format("%02dh %02dm %02ds", h, m, s);

        //label.setText(time);
    }
}
