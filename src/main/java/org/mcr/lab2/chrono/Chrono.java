package org.mcr.lab2.chrono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Chrono {
    private boolean enabled;
    private Instant start;
    private long restartOffset;

    public Chrono() {
        this.enabled = false;
        this.restartOffset = 0;
    }

    public void start() {
        this.enabled = true;
        this.start = Instant.now();
    }

    public void stop() {
        this.enabled = false;
        this.restartOffset = this.start.until(Instant.now(), ChronoUnit.SECONDS) + restartOffset;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public void reset() {
        this.start = Instant.now();
        this.restartOffset = 0;
    }

    public long getElapsedTime() {
        if (this.start == null) {
            return 0;
        }
        return restartOffset + this.start.until(Instant.now(), ChronoUnit.SECONDS);
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
