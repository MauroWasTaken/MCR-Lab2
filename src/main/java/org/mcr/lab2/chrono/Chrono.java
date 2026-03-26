package org.mcr.lab2.chrono;

public class Chrono extends ChronoSubject {
    private final int id;
    private boolean enabled;
    private int counter;
    private long elapsedTime;

    // TODO: add Timer (enonce says util.Timer, but Swing.Timer has pause/restart methods...)
    // TODO: link timer with elapsedTime. Or replace elapsedTime altogether.
    // TODO: or perhaps, have a timer that calls tick() every second.

    public Chrono(int id) {
        this.id = id;
        this.enabled = false;
        this.counter = 0;
        this.elapsedTime = 0;
    }

    private void tick() {
        this.elapsedTime += 1;
    }

    public void start() {
        this.enabled = true;
        notifyView();
    }

    public void stop() {
        this.enabled = false;
        notifyView();
    }

    public void toggle() {
        this.enabled = !this.enabled;
        notifyView();
    }

    public void reset() {
        this.elapsedTime = 0;
        notifyView();
    }

    public long getElapsedTime() {
        return this.elapsedTime;
    }
}
