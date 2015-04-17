package de.tahigames.demondefense.game.world.enemies;

import de.tahigames.demondefense.engine.ai.AIComponent;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;

/**
 * Created by Mirco on 16.04.2015.
 */
public class EnemyAI extends AIComponent{

    private Enemy enemy;
    private PhysicsComponent physicsComponent;

    public EnemyAI(Enemy enemy, PhysicsComponent physicsComponent){
        this.enemy = enemy;
        this.physicsComponent = physicsComponent;
    }

    @Override
    public void think(float delta) {



        physicsComponent.getVelocity().set(10,0);
    }
}
