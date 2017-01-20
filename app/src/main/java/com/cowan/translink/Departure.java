package com.cowan.translink;

import java.util.GregorianCalendar;

/**
 * Created by Cowan on 1/19/2017.
 */

public class Departure {
    private final String time;
    private final int stopNumber;
    private final int routeNumber;


    Departure(String time, int stopNumber, int routeNumber) {
        this.time = time;
        this.stopNumber = stopNumber;
        this.routeNumber = routeNumber;
    }

    public String getTime() {
        return time;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    @Override
    public String toString() {
        return stopNumber + " [" + routeNumber + "] " + time;
    }
}
