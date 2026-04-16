package org.mcr.lab2;

import org.mcr.lab2.display.controlsview.ControlsView;

import java.util.Scanner;

public class FancyClocks {
    public static void main(String[] args) {
        // User input
        final Scanner input = new Scanner(System.in);
        System.out.println("Please input the number of clocks you want [1-9]: ");
        int nbClocks;
        while (true) {
            if (input.hasNextInt()) {
                nbClocks = input.nextInt();
                if (nbClocks < 1 || nbClocks > 9) {
                    System.out.println("Between 1 and 9 please: ");
                } else {
                    break;
                }
            } else {
                System.out.println("A number, pretty please [1-9]: ");
                input.nextLine();
            }
        }
        // Start the controls view
        final ControlsView controlsView = new ControlsView(nbClocks);
        controlsView.setVisible(true);
    }
}
