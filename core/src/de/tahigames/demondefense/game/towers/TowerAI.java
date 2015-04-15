package de.tahigames.demondefense.game.towers;

import java.util.List;

import de.tahigames.demondefense.engine.ai.AIComponent;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;
import de.tahigames.demondefense.game.enemies.Enemy;

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
    }

    @Override
    public void think(float delta) {
        //bestimme ziel
        List<PhysicsComponent> collisions = physicsComponent.getCurrentCollisions();
        if(target == null || !contains(collisions, target)){
            determineTarget(collisions);
        }
        //überprüfe ob schießbereit
        float attackSpeed = tower.getAttributes(tower.getLevel()).getAttackSpeed();

        if(passedTime<= (1 / attackSpeed))
            passedTime += delta;

        if(target != null){
            if(passedTime >= (1 / attackSpeed)){
                tower.shoot(target);
                passedTime -= (1 / attackSpeed);
            }
        }
    }

    private void determineTarget(List<PhysicsComponent> collisions){
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
