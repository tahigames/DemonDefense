package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

import de.tahigames.demondefense.engine.ai.AIComponent;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;

/**
 * Created by Mirco on 16.04.2015.
 */
public class EnemyAI extends AIComponent{

    private Enemy enemy;
    private PhysicsComponent physicsComponent;
    private Queue<Vector2> path;

    public EnemyAI(Enemy enemy, PhysicsComponent physicsComponent, Queue<Vector2> path){
        this.enemy = enemy;
        this.physicsComponent = physicsComponent;
        this.path = path;
    }

    @Override
    public void think(float delta) {

        physicsComponent.getVelocity().set(10,0);
    }
}
