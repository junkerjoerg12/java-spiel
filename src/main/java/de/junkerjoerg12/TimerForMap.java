package de.junkerjoerg12;

public class TimerForMap {
    private long startTime;
    private Game game;

    public TimerForMap(Game game) {
        this.game = game;
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
