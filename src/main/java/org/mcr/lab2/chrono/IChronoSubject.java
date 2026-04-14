package org.mcr.lab2.chrono;

import org.mcr.lab2.display.clocks.View;

public interface IChronoSubject {
    void attach(View view);
    void detach(View view);
    void notifyViews();
}
