package org.mcr.lab2;

import org.mcr.lab2.display.controlsview.ControlsView;

public class FancyClocks {
    public static void main(String[] args) {
        final ControlsView controlsView = new ControlsView(2);
        controlsView.setVisible(true);
    }
}
