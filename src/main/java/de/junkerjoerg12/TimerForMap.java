package de.junkerjoerg12;

public class TimerForMap {
    private long startTime;
    private Game game;

    public TimerForMap(Game game) {
        this.game = game;
        startTime = System.currentTimeMillis();
    }

    public long calculatecurrenttime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }

}
