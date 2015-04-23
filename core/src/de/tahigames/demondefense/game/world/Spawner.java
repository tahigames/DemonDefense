package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

import de.tahigames.demondefense.engine.core.Entity;

/**
 * Created by Marcel on 22.04.2015.
 */
public class Spawner extends Entity {

    private int levelNr;
    private Wave[] waves;
    private Queue<Vector2> path;

    public Spawner(float x, float y, Wave[] waves, Queue<Vector2> path) {
        super(x, y);
        this.waves = waves;
        this.path = path;
        levelNr = 1;
        addComponent(new SpawnerAI(this));
    }

    public void nextWave(){
        levelNr++;
        addComponent(new SpawnerAI(this));
    }

    public Queue<Vector2> getPath(){
        return path;
    }

    public Wave getCurrentWave(){
        return waves[levelNr];
    }
}
