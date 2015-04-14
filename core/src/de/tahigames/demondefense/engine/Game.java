package de.tahigames.demondefense.engine;

import com.badlogic.gdx.Input;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class Game {

    public abstract void init(Core core);
    public abstract void handleInput(Input input);

}
