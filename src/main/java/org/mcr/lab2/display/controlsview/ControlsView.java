package org.mcr.lab2.display.controlsview;

import org.mcr.lab2.chrono.ChronoSubject;
import org.mcr.lab2.display.clocks.Arab;
import org.mcr.lab2.display.clocks.Digital;
import org.mcr.lab2.display.clocks.Roman;
import org.mcr.lab2.display.clocks.View;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.HashMap;
import java.util.Map;

public class ControlsView extends JFrame {
    private final int nbChronos;

    private final ChronoSubject[] chronos;

    public ControlsView(int nbChronos) {
        super("Panneau de contrôle");

        if (nbChronos < 1 | nbChronos > 9) {
            throw new IllegalArgumentException("Number of chronometers must be between 1 and 9");
        }

        this.nbChronos = nbChronos;

        chronos = new ChronoSubject[nbChronos];
        final JButton[] startButtons = new JButton[nbChronos];
        final JButton[] stopButtons = new JButton[nbChronos];
        final JButton[] resetButtons = new JButton[nbChronos];
        final JButton[] romanButtons = new JButton[nbChronos];
        final JButton[] arabicButtons = new JButton[nbChronos];
        final JButton[] digitalButtons = new JButton[nbChronos];


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
            // Action buttons
            startButtons[row].addActionListener(e -> chronos[finalRow].start());
            stopButtons[row].addActionListener(e -> chronos[finalRow].stop());
            resetButtons[row].addActionListener(e -> chronos[finalRow].reset());

            // Create clock views
            romanButtons[row].addActionListener(e -> createRomanClockView(finalRow));
            arabicButtons[row].addActionListener(e -> createArabClockView(finalRow));
            digitalButtons[row].addActionListener(e -> createDigitalClockView(finalRow));

            // Store and set buttons on UI
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

        // bottom line, controls to get all views in a given clock format
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

        final JButton allRomanButton = new JButton("Cadran romain");
        panel.add(allRomanButton, gbc);
        gbc.gridx = 5;

        final JButton allArabicButton = new JButton("Cadran arabe");
        panel.add(allArabicButton, gbc);
        gbc.gridx = 6;

        final JButton allDigitalButton = new JButton("Numérique");
        panel.add(allDigitalButton, gbc);

        // Set the actions for the all-same-clocks views
        allRomanButton.addActionListener(e -> {
            // Create frame
            final JFrame frame = new JFrame("");
            frame.setSize(200*nbChronos, 235);
            // Create panel
            final JPanel clocksPanel = new JPanel();
            // Create, register clock views
            final Map<ChronoSubject, View> views = new HashMap<>();
            for (ChronoSubject chrono : chronos) {
                final JPanel roman = new Roman(chrono);
                views.put(chrono, (View) roman);
                clocksPanel.add(roman);
            }
            // Set common attributes
            setCommonMultipleClockViewsAttributes(frame, clocksPanel, views);
        });

        allArabicButton.addActionListener(e -> {
            // Create frame
            final JFrame frame = new JFrame("");
            frame.setSize(200*nbChronos, 235);
            // Create panel
            final JPanel clocksPanel = new JPanel();
            // Create, register clock views
            final Map<ChronoSubject, View> views = new HashMap<>();
            for (ChronoSubject chrono : chronos) {
                final JPanel arab = new Arab(chrono);
                views.put(chrono, (View) arab);
                clocksPanel.add(arab);
            }
            // Set common attributes
            setCommonMultipleClockViewsAttributes(frame, clocksPanel, views);
        });

        allDigitalButton.addActionListener(e -> {
            // Create frame
            final JFrame frame = new JFrame("");
            frame.setSize(200*nbChronos, 235);
            // Create panel
            final JPanel clocksPanel = new JPanel();
            // Create, register clock views
            final Map<ChronoSubject, View> views = new HashMap<>();
            for (ChronoSubject chrono : chronos) {
                final JPanel digital = new Digital(chrono);
                views.put(chrono, (View) digital);
                clocksPanel.add(digital);
            }
            // Set common attributes
            setCommonMultipleClockViewsAttributes(frame, clocksPanel, views);
        });

        // Final touch ups for the controlsView window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        pack();
    }

    private void updateLayout(JFrame frame, JPanel panel, int count) {
        // Util method for dynamic all-clocks-view window scaling
        int width = frame.getWidth();
        int height = frame.getHeight();

        // Decide columns based on aspect ratio
        final int cols;

        // When the window is as wide or wider than it is tall
        if (width >= height) {
            // As many columns as there are clocks
            cols = count;
        } else {
            // When the window is taller than it is wide
            // One vertical column
            cols = 1;
        }

        // Set the row count according to column count and refresh the layout
        final int rows = (int) Math.ceil((double) count / cols);

        panel.setLayout(new GridLayout(rows, cols));
        panel.revalidate();
    }

    /* Singular clock views methods */
    private void createRomanClockView(int row) {
        final JFrame frame = new JFrame("");
        frame.setSize(200, 235);
        final JPanel roman = new Roman(chronos[row]);
        setCommonSingularClockViewAttributes(frame, roman, row);
    }

    private void createArabClockView(int row) {
        final JFrame frame = new JFrame("");
        frame.setSize(200, 235);
        final JPanel arab = new Arab(chronos[row]);
        setCommonSingularClockViewAttributes(frame, arab, row);
    }

    private void createDigitalClockView(int row) {
        final JFrame frame = new JFrame("");
        frame.setSize(200, 235);
        final JPanel digital = new Digital(chronos[row]);
        setCommonSingularClockViewAttributes(frame, digital, row);
    }

    private void setCommonSingularClockViewAttributes(JFrame frame, JPanel clockView, int row) {
        frame.setContentPane(clockView);
        frame.setResizable(false);

        // When closing the window, have the chrono detach the view
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                chronos[row].detach((View) clockView);
            }
        });
        frame.setVisible(true);
    }

    /* Multiple clock views methods */
    private void setCommonMultipleClockViewsAttributes(JFrame frame, JPanel clocksPanel, Map<ChronoSubject, View> subjectViewMap) {
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
                for (Map.Entry<ChronoSubject, View> entries : subjectViewMap.entrySet()) {
                    entries.getKey().detach(entries.getValue());
                }
            }
        });
        frame.setVisible(true);
    }
}
