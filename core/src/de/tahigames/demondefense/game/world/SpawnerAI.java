package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.Gdx;

import de.tahigames.demondefense.engine.core.ai.AIComponent;
import de.tahigames.demondefense.game.world.enemies.EnemyFactory;

/**
 * Created by Marcel on 22.04.2015.
 */
public class SpawnerAI extends AIComponent {

    private static float spawnDelay = 2f;

    private float passedTime;
    private int spawnedEnemies;
    private Spawner spawner;
    private Wave wave;

    public SpawnerAI(Spawner spawner) {
        this.spawner = spawner;
        this.wave = spawner.getCurrentWave();
        passedTime = 0f;
        spawnedEnemies = 0;
    }

    @Override
    public void think(float delta) {
        passedTime += delta;
        if(passedTime >= spawnDelay){
            spawnEnemy();
            passedTime -= spawnDelay;
        }
    }

    private void spawnEnemy(){
        if(spawnedEnemies < wave.getCount()){
            spawner.getParent().addChild(EnemyFactory.create(spawner.getX(), spawner.getY(), wave.getType(), spawner.getPath()));
            Gdx.app.log("spawner", "SPAWN! - " + spawnedEnemies);
            spawnedEnemies++;
        }else{
            spawner.removeComponent(this);
        }
    }

}
