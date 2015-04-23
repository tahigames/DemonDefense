package de.tahigames.demondefense.game.world.towers;

import java.util.List;

import de.tahigames.demondefense.engine.core.ai.AIComponent;
import de.tahigames.demondefense.engine.core.physics.PhysicsComponent;
import de.tahigames.demondefense.game.world.enemies.Enemy;

/**
 * Created by Marcel on 15.04.2015.
 */
public class TowerAI extends AIComponent{

    private PhysicsComponent physicsComponent;
    private Tower tower;
    private Enemy target;
    private float passedTime;

    public TowerAI(PhysicsComponent physicsComponent, Tower tower){
        this.physicsComponent = physicsComponent;
        this.tower = tower;
        passedTime = 1f / tower.getAttributes(tower.getLevel()).getAttackSpeed();
    }

    @Override
    public void think(float delta) {
        List<PhysicsComponent> collisions = physicsComponent.getCurrentCollisions();

        //bestimme ziel
        if(target == null || !contains(collisions, target)){
            determineNewTarget(collisions);
        }
        //überprüfe ob schießbereit
        float attackSpeed = tower.getAttributes(tower.getLevel()).getAttackSpeed();

        if(passedTime < (1 / attackSpeed))
            passedTime += delta;

        if(target != null){
            if(passedTime >= (1 / attackSpeed)){
                tower.shoot(target);
                passedTime -= (1 / attackSpeed);
            }
        }
    }

    private void determineNewTarget(List<PhysicsComponent> collisions){
        target = null;
        float min = Float.MAX_VALUE;
        for (PhysicsComponent c : collisions){
            float stepX = c.getParent().getX() - getParent().getX();
            float stepY = c.getParent().getY() - getParent().getY();

            float length = (float) Math.sqrt(stepX * stepX + stepY * stepY);
            if(length < min){
                min = length;

                target = (Enemy) c.getParent();
            }
        }
    }

    private boolean contains(List<PhysicsComponent> collisions, Enemy target){
        for (PhysicsComponent c : collisions){
            if(c.getParent() == target)
                return true;
        }
        return false;
    }
}
