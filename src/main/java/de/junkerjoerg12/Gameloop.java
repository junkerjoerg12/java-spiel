package de.junkerjoerg12;

public class Gameloop extends Thread {

    long delay;
    Game game;
    boolean run = true;

    public Gameloop(long delay, Game game) {
        this.delay = delay;
        this.game = game;
    }

    @Override
    public void run() {
        super.run();
        loop();
    }

    private void loop() {
        while (run) {
            game.tick();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void pause() {
        run = false;
    }

    public void go() {
        run = true;
        loop();
    }

}
