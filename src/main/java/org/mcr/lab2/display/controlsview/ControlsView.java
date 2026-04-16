package org.mcr.lab2.display.controlsview;

import org.mcr.lab2.chrono.ChronoSubject;
import org.mcr.lab2.display.clocks.Arab;
import org.mcr.lab2.display.clocks.Digital;
import org.mcr.lab2.display.clocks.Roman;
import org.mcr.lab2.display.clocks.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControlsView extends JFrame {
    private final int nbChronos;

    private final ChronoSubject[] chronos;

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

        chronos = new ChronoSubject[nbChronos];
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
            chronos[row] = new ChronoSubject(row + 1); //sending row so i can use it on the labels
            startButtons[row] = new JButton("Démarrer");
            stopButtons[row] = new JButton("Arrêter");
            resetButtons[row] = new JButton("Réinitialiser");
            romanButtons[row] = new JButton("Cadran romain");
            arabicButtons[row] = new JButton("Cadran arabe");
            digitalButtons[row] = new JButton("Numérique");

            // add listeners
            int finalRow = row;
            startButtons[row].addActionListener(e -> {
                chronos[finalRow].start();
            });
            stopButtons[row].addActionListener(e -> {
                chronos[finalRow].stop();
            });
            resetButtons[row].addActionListener(e -> {
                chronos[finalRow].reset();
            });
            romanButtons[row].addActionListener(e -> {
                JFrame frame = new JFrame("");
                frame.setSize(200, 235);
                final JPanel roman = new Roman(chronos[finalRow]);
                frame.setContentPane(roman);
                frame.setResizable(false);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        chronos[finalRow].detach((View) roman);
                    }
                });
                frame.setVisible(true);
            });
            arabicButtons[row].addActionListener(e -> {
                JFrame frame = new JFrame("");
                frame.setSize(200, 235);
                final JPanel arab = new Arab(chronos[finalRow]);
                frame.setContentPane(arab);
                frame.setResizable(false);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        chronos[finalRow].detach((View) arab);
                    }
                });
                frame.setVisible(true);
            });
            digitalButtons[row].addActionListener(e -> {
                JFrame frame = new JFrame("");
                frame.setSize(200, 235);
                final JPanel digital = new Digital(chronos[finalRow]);
                frame.setContentPane(digital);
                frame.setResizable(false);

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        super.windowClosing(e);
                        chronos[finalRow].detach((View) digital);
                    }
                });
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

        allRomanButton.addActionListener(e -> {
            JFrame frame = new JFrame("");
            frame.setSize(200*nbChronos, 235);
            JPanel clocksPanel = new JPanel();
            java.util.Map<ChronoSubject, View> views = new HashMap<>();
            for (ChronoSubject chrono : chronos) {
                final JPanel roman = new Roman(chrono);
                views.put(chrono, (View) roman);
                clocksPanel.add(roman);
            }

            frame.setContentPane(clocksPanel);

            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    updateLayout(frame, clocksPanel, nbChronos);
                }
            });

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    for (Map.Entry<ChronoSubject, View> entries : views.entrySet()) {
                        entries.getKey().detach(entries.getValue());
                    }
                }
            });
            frame.setVisible(true);

        });

        allArabicButton.addActionListener(e -> {
            JFrame frame = new JFrame("");
            frame.setSize(200*nbChronos, 235);
            JPanel clocksPanel = new JPanel();
            java.util.Map<ChronoSubject, View> views = new HashMap<>();
            for (ChronoSubject chrono : chronos) {
                final JPanel arab = new Arab(chrono);
                views.put(chrono, (View) arab);
                clocksPanel.add(arab);
            }

            frame.setContentPane(clocksPanel);

            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    updateLayout(frame, clocksPanel, nbChronos);
                }
            });

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    for (Map.Entry<ChronoSubject, View> entries : views.entrySet()) {
                        entries.getKey().detach(entries.getValue());
                    }
                }
            });
            frame.setVisible(true);

        });

        allDigitalButton.addActionListener(e -> {
            JFrame frame = new JFrame("");
            frame.setSize(200*nbChronos, 235);
            JPanel clocksPanel = new JPanel();
            java.util.Map<ChronoSubject, View> views = new HashMap<>();
            for (ChronoSubject chrono : chronos) {
                final JPanel digital = new Digital(chrono);
                views.put(chrono, (View) digital);
                clocksPanel.add(digital);
            }

            frame.setContentPane(clocksPanel);

            frame.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    updateLayout(frame, clocksPanel, nbChronos);
                }
            });

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    for (Map.Entry<ChronoSubject, View> entries : views.entrySet()) {
                        entries.getKey().detach(entries.getValue());
                    }
                }
            });
            frame.setVisible(true);

        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
    }

    private void updateLayout(JFrame frame, JPanel panel, int count) {

        int width = frame.getWidth();
        int height = frame.getHeight();

        // Decide columns based on aspect ratio
        int cols;

        if (width > height) {
            cols = count; // horizontal
        } else if (height > width) {
            cols = 1; // vertical
        } else {
            cols = (int) Math.sqrt(count); // square-ish
        }

        int rows = (int) Math.ceil((double) count / cols);

        panel.setLayout(new GridLayout(rows, cols));
        panel.revalidate();
    }
}
