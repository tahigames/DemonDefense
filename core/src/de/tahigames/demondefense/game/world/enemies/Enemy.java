package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.math.Vector2;

import java.util.Queue;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.physics.AaBb;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.rendering.AnimationComponent;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Enemy extends Entity {

    public static final int SIZE = 16;

    private int health;
    private Queue<Vector2> path;

    public Enemy(float x, float y, int health, AnimationComponent renderComponent, Queue<Vector2> path) {
        super(x, y);
        this.health = health;
        this.path = path;
        addComponent(renderComponent);
        float halfWidth = SIZE / 2;
        float halfHeight = SIZE / 2;
        PhysicsComponent physicsComponent = new PhysicsComponent(new AaBb(getX() - halfWidth, getY() - halfHeight, getX() + halfWidth, getY() + halfHeight)) {
            @Override
            public boolean canCollideWith(Entity e) {
                return false;
            }
        };
        addComponent(physicsComponent);
        addComponent(new EnemyAI(this, physicsComponent));
    }

    public void getDamage(int damage){
        health -= damage;
    }

    public Queue<Vector2> getPath(){
        return path;
    }
}
