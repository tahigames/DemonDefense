package de.tahigames.demondefense.game.enemies;

import de.tahigames.demondefense.engine.ai.AIComponent;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;

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
