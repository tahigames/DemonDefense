package de.tahigames.demondefense.game.world;

import de.tahigames.demondefense.game.world.enemies.EnemyFactory;

/**
 * Created by Marcel on 22.04.2015.
 */
public class Wave {
    private int count;
    private EnemyFactory.MONSTER_TYPE type;

    public Wave(int count, EnemyFactory.MONSTER_TYPE type) {
        this.count = count;
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public EnemyFactory.MONSTER_TYPE getType() {
        return type;
    }
}
