package org.mcr.lab2.display.controlsview;

import org.mcr.lab2.display.DefaultAbstractWindow;
import org.mcr.lab2.display.clocks.Roman;

import javax.swing.*;
import java.awt.*;

public class ControlsView extends JFrame {
    private final int nbChronos;

    //private final Chrono[] chronos;

    private final JButton[] startButtons;
    private final JButton[] stopButtons;
    private final JButton[] resetButtons;
    private final JButton[] romanButtons;
    private final JButton[] arabicButtons;
    private final JButton[] digitalButtons;
    private final JButton allRomanButton = new JButton("Cadran romain");
    private final JButton allArabicButton = new JButton("Cadran arabe");
    private final JButton allDigitalButton = new JButton("Numérique");

    public ControlsView(int nbChronos) {
        super("Panneau de contrôle");

        if (nbChronos < 1 | nbChronos > 9) {
            throw new IllegalArgumentException("Number of chronometers must be between 1 and 9");
        }

        this.nbChronos = nbChronos;

        //chronos = new Chrono[nbChronos];
        startButtons = new JButton[nbChronos];
        stopButtons = new JButton[nbChronos];
        resetButtons = new JButton[nbChronos];
        romanButtons = new JButton[nbChronos];
        arabicButtons = new JButton[nbChronos];
        digitalButtons = new JButton[nbChronos];


        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4); //margins
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int row = 0; row < nbChronos; row++) {
            //chronos[row] = new Chrono(row + 1); //sending row so i can use it on the labels
            startButtons[row] = new JButton("Démarrer");
            stopButtons[row] = new JButton("Arrêter");
            resetButtons[row] = new JButton("Réinitialiser");
            romanButtons[row] = new JButton("Cadran romain");
            arabicButtons[row] = new JButton("Cadran arabe");
            digitalButtons[row] = new JButton("Numérique");

            // add listeners
            startButtons[row].addActionListener(e -> {
                //chronos[row].start();
            });
            stopButtons[row].addActionListener(e -> {
                //chronos[row].stop();
            });
            resetButtons[row].addActionListener(e -> {
                //chronos[row].reset();
            });
            romanButtons[row].addActionListener(e -> {
                JFrame frame = new JFrame("");
                //frame.add(new Roman(chronos[row]));
                frame.setSize(200, 200);
                frame.setVisible(true);
            });
            arabicButtons[row].addActionListener(e -> {
                JFrame frame = new JFrame("");
                //frame.add(new Arab(chronos[row]));
                frame.setSize(200, 200);
                frame.setVisible(true);
            });
            digitalButtons[row].addActionListener(e -> {
                JFrame frame = new JFrame("");
                //frame.add(new Digital(chronos[row]));
                frame.setSize(200, 200);
                frame.setVisible(true);
            });
            JButton[] rowButtons = {
                    startButtons[row], stopButtons[row], resetButtons[row],
                    romanButtons[row], arabicButtons[row], digitalButtons[row]
            };

            gbc.gridy = row;
            gbc.weightx = 0;

            // left label
            gbc.gridx = 0;
            panel.add(new JLabel("Chrono #" + (row + 1)), gbc);

            // buttons
            gbc.weightx = 1.0;
            for (int col = 0; col < rowButtons.length; col++) {
                gbc.gridx = col + 1;
                panel.add(rowButtons[col], gbc);
            }
        }
        // bottom line
        gbc.gridy = nbChronos;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Tous les chronos"), gbc);
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 4;
        panel.add(allRomanButton, gbc);
        gbc.gridx = 5;
        panel.add(allArabicButton, gbc);
        gbc.gridx = 6;
        panel.add(allDigitalButton, gbc);

        getContentPane().add(panel); //adds panel onto self (jframe)

    }
}
