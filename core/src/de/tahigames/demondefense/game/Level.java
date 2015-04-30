package de.tahigames.demondefense.game;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Mirco on 21.04.2015.
 */
public class Level {

    private Map map;

    public Level(String mapName, Entity root) {
        map = new Map(mapName);
    }

    public void startRound() {
    }

}
