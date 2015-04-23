package de.tahigames.demondefense.engine.core.ai;

import de.tahigames.demondefense.engine.core.Engine;

/**
 * Created by Mirco on 15.04.2015.
 */
public class AIEngine extends Engine<AIComponent> {

    private float thinkTime;
    private float timePassed;

    public AIEngine(int simulationsPerSecond){
        thinkTime = 1f / simulationsPerSecond;
    }

    public void think(float delta) {
        timePassed += delta;

        while(timePassed > thinkTime){
            for (int i = getComponents().size() - 1; i >= 0; i--) {
                getComponents().get(i).think(thinkTime);
            }
            timePassed -= thinkTime;
        }
    }

}
