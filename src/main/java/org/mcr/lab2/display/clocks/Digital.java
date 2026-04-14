package org.mcr.lab2.display.clocks;

import org.mcr.lab2.chrono.ChronoSubject;

import javax.swing.*;
import java.awt.*;

public class Digital extends JPanel implements View {
    private final ChronoSubject chrono;
    private final JLabel label;

    private final String TEMPLATE = "Chrono %d: %02dh %02dm %02ds";

    public Digital(ChronoSubject chrono) {
        super(new GridBagLayout());
        this.chrono = chrono;
        this.chrono.attach(this);
        label = new JLabel(TEMPLATE.formatted(chrono.getId(), 0, 0, 0), SwingConstants.CENTER);
        add(label);
    }

    @Override
    public void update() {
        final long elapsedSeconds = chrono.getElapsedTime();

        long h = elapsedSeconds / 3600;
        long m = (elapsedSeconds % 3600) / 60;
        long s = elapsedSeconds % 60;

        String time = String.format(TEMPLATE, chrono.getId(), h, m, s);

        if (label != null) {
            label.setText(time);
            add(label);
        }
    }
}
