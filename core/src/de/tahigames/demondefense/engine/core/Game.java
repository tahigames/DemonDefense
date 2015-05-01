package de.tahigames.demondefense.engine.core;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Game {

    private Core core;
    private Screen screen;

    public Game(Screen initialScreen) {
        this.screen = initialScreen;
    }

    void init(Core core){
        this.core = core;
        screen.init(this, core);
    }

    void dispose(){
        screen.dispose();
    }

    public void setScreen(Screen screen) {
        if(core == null)
            throw new IllegalStateException("You cannot set a new screen before the game has been initialized.");

        this.screen.dispose();
        this.screen = screen;
        this.screen.init(this, core);
    }
}
