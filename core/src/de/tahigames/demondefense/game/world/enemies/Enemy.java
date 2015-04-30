package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.physics.AaBb;
import de.tahigames.demondefense.engine.core.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.core.rendering.AnimationComponent;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Enemy extends Entity {

    public static final int SIZE = 16;

    private Healthbar healthbar;

    private final int maxHealth;
    private float currentHealth;

    public Enemy(float x, float y, int maxHealth, AnimationComponent renderComponent, List<Vector2> path) {
        super(x, y);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
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
        addComponent(new EnemyAI(this, physicsComponent, path));

        this.healthbar = new Healthbar(0, 10, maxHealth, 5);
        addChild(healthbar);
    }

    public void doDamage(float damage){
        currentHealth -= damage;
        healthbar.setHealth(currentHealth);
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }
}
