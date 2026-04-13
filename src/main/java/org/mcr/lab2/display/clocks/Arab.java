package org.mcr.lab2.display.clocks;

import javax.swing.*;

public class Arab implements View {
    private static final String imagePath = "";
    JPanel panel;
    // Chrono chrono
    Clock clock;
    Arab (/*Chrono chrono,*/ JPanel panel){
        this.panel = panel;
        //this.chrono = chrono;
        this.clock = ClockFactory.getInstance().createClock(imagePath);
    }

    @Override
    public void toggle() {
        //chrono.toggle();
    }

    @Override
    public void update() {
        //clock.draw(panel, chrono.getElapsedTime());
    }
}
