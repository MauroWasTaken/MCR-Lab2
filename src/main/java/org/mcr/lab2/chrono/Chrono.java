package org.mcr.lab2.chrono;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Chrono {
    // Class that performs the actual time measurement

    private boolean enabled;
    // Point-in-time based time measurement, resilient to DST changes
    private Instant start;
    // When pausing/resuming, keeps track of the elapsed time until stopping
    private long restartOffset; // seconds

    public Chrono() {
        this.enabled = false;
        this.restartOffset = 0;
    }

    public void start() {
        this.enabled = true;
        this.start = Instant.now();
    }

    public void stop() {
        if (this.enabled) {
            this.enabled = false;
            if (this.start != null) {
                // Save elapsed time in seconds
                this.restartOffset = this.start.until(Instant.now(), ChronoUnit.SECONDS) + restartOffset;
            }
        }
    }

    public void reset() {
        this.start = Instant.now();
        this.restartOffset = 0;
    }

    public long getElapsedTime() {
        if (this.start == null) {
            return 0;
        }
        // Return total time, including potential offset if any pause has been requested until now
        return restartOffset + this.start.until(Instant.now(), ChronoUnit.SECONDS);
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
