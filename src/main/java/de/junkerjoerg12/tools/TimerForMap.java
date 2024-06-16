package de.junkerjoerg12.tools;

public class TimerForMap {
    private long startTime;

    public TimerForMap() {
        startTime = System.currentTimeMillis();
    }

    public long calculateCurrentTimeInMin() {
        return ((System.currentTimeMillis() - startTime) / 1000) / 60;
    }

    public long calculateCurrentTimeInS() {
        return ((System.currentTimeMillis() - startTime) / 1000) % 60;
    }

    public long calculateCurrentTimeInMs() {
        return ((System.currentTimeMillis() - startTime) % 1000) / 10;
    }

}
