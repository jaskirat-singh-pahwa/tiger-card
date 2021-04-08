package com.jaskirat.tigercard;

import java.time.DayOfWeek;
import java.time.LocalTime;


public class Journey {
    private final DayOfWeek dayOfWeek;
    private final LocalTime time;
    private final Zone fromZone;
    private final Zone toZone;

    public Journey(DayOfWeek dayOfWeek, LocalTime time, Zone fromZone, Zone toZone) {
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.fromZone = fromZone;
        this.toZone = toZone;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public Zone getFromZone() {
        return fromZone;
    }

    public Zone getToZone() {
        return toZone;
    }
}
