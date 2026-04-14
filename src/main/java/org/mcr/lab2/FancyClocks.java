package org.mcr.lab2;

import org.mcr.lab2.display.controlsview.ControlsView;

import javax.swing.*;

public class FancyClocks {

    public static void main(String[] args) {
        final ControlsView controlsView = new ControlsView(3);
        controlsView.setVisible(true);
    }
}
