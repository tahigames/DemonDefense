package de.tahigames.demondefense.game.world.towers.projectiles;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.ai.AIComponent;
import de.tahigames.demondefense.engine.core.physics.PhysicsComponent;
import de.tahigames.demondefense.game.world.enemies.Enemy;

/**
 * Created by Marcel on 15.04.2015.
 */
public class ProjectileAI extends AIComponent {

    private static final float PROJECTILE_SPEED = 128.0f;
    private PhysicsComponent physicsComponent;
    private Enemy target;

    public ProjectileAI(PhysicsComponent physicsComponent, Enemy target){
        this.physicsComponent = physicsComponent;
        this.target = target;
    }

    @Override
    public void think(float delta) {
        if(target.isDead()){
            getParent().getParent().removeChild(getParent());
        }

        float stepX = target.getTransformedX() - getParent().getTransformedX();
        float stepY = target.getTransformedY() - getParent().getTransformedY();

        float length = (float) Math.sqrt(stepX * stepX + stepY * stepY);

        stepX = (stepX / length) * PROJECTILE_SPEED;
        stepY = (stepY / length) * PROJECTILE_SPEED;

        physicsComponent.getVelocity().set(stepX, stepY);
    }
}
