package org.mcr.lab2.display.clocks;

import java.util.HashMap;

public class ClockFactory {
    private final HashMap<String, Clock> clockCache = new HashMap<>();
    private static ClockFactory instance;
    public static ClockFactory getInstance(){
        if (instance == null){
            instance = new ClockFactory();
        }
        return instance;
    }
    public Clock createClock(String imagePath){
        return clockCache.computeIfAbsent(imagePath, k-> new Clock(imagePath));
    }
}
