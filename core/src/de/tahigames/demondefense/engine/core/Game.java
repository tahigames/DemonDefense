package de.tahigames.demondefense.engine.core;

import com.badlogic.gdx.Gdx;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Game {

    private Core core;
    private Screen screen;

    public Game(Screen initialScreen) {
        this.screen = initialScreen;
    }

    void init(Core core) {
        this.core = core;
        setScreen(screen);
    }

    void dispose() {
        screen.end();
    }

    public void setScreen(Screen screen) {
        if (core == null)
            throw new IllegalStateException("You cannot set a new screen before the game has been initialized.");

        if (this.screen != null)
            this.screen.end();
        this.screen = screen;
        Gdx.input.setInputProcessor(screen);
        this.screen.start(this, core);
    }

    Core getCore() {
        return core;
    }
}
