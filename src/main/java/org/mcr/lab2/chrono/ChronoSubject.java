package org.mcr.lab2.chrono;

import org.mcr.lab2.display.clocks.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChronoSubject implements IChronoSubject {
    private final List<View> observingViews;
    private final int id;

    private final Chrono chrono;
    private final Timer timer;

    private long elapsedTime;

    public ChronoSubject(int id) {
        this.id = id;
        this.chrono = new Chrono();
        this.observingViews = new ArrayList<>();
        this.timer = new Timer();
    }

    public void attach(View view) {
        this.observingViews.add(view);
        System.out.printf("Chrono %d attached a view. Now has %d\n", this.getId(), this.observingViews.size());
    }

    public void detach(View view) {
        this.observingViews.remove(view);
        System.out.printf("Chrono %d detached a view. Remains %d\n", this.getId(), this.observingViews.size());
    }

    public void notifyViews() {
        for (View view : this.observingViews) {
            view.update();
        }
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }

    public int getId() {
        return this.id;
    }

    public void start() {
        this.chrono.start();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (chrono.isEnabled()) {
                    poolChrono();
                }
            }
        }, 0, 1L);
    }

    public void stop() {
        this.chrono.stop();
    }

    public void reset() {
        this.chrono.reset();
        notifyViews();
    }

    private void poolChrono() {
        this.elapsedTime = chrono.getElapsedTime();
        notifyViews();
    }
}
