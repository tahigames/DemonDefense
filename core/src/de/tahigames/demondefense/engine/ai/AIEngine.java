package de.tahigames.demondefense.engine.ai;

import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 15.04.2015.
 */
public class AIEngine extends Engine<AIComponent> {

    public void think(float delta) {
        for (AIComponent c : getComponents()){
            c.think(delta);
        }
    }

}
