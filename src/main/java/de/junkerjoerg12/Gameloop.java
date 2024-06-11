package de.junkerjoerg12;

public class Gameloop extends Thread {

    long delay;
    Game game;

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
        while (true) {
            game.tick();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
