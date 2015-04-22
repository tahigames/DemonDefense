package de.tahigames.demondefense.game.world;

import de.tahigames.demondefense.engine.Entity;

/**
 * Created by Marcel on 22.04.2015.
 */
public class Spawner extends Entity{

    private int levelNr;
    private Wave[] waves;

    public Spawner(float x, float y, Wave[] waves) {
        super(x, y);
        this.waves = waves;
        levelNr = 1;
        addComponent(new SpawnerAI(this));
    }

    public void nextWave(){
        levelNr++;
        addComponent(new SpawnerAI(this));
    }

    public Wave getCurrentWave(){
        return waves[levelNr];
    }
}
