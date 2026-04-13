package org.mcr.lab2;

import org.mcr.lab2.display.controlsview.ControlsView;

import javax.swing.*;

public class FancyClocks {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ControlsView view = new ControlsView(3);
            view.setVisible(true);
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

}
