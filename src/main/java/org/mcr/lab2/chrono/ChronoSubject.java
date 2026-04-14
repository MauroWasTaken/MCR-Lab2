package org.mcr.lab2.chrono;

import org.mcr.lab2.display.clockview.View;

public class ChronoSubject implements IChronoSubject {
    private View observedView;

    public void attach(View view) {
        this.observedView = view;
    }

    public void detach(View view) {
        if (view != this.observedView) {
            System.out.println("Supplied view is not the correct one.");
        }
        this.observedView = null;
    }

    public void notifyView() {
        this.observedView.update();
    }
}
