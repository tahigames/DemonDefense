package de.tahigames.demondefense.game;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Game;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {
    private Map map;

    @Override
    public void init(Core core) {
        map = new Map(20, 20);
        core.getRoot().addChild(map);
    }
}
