package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Queue;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.physics.AaBb;
import de.tahigames.demondefense.engine.core.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.core.rendering.AnimationComponent;
import de.tahigames.demondefense.engine.core.rendering.DrawComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.ShapeRenderComponent;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Enemy extends Entity {

    public static final int SIZE = 16;

    private int health;

    public Enemy(float x, float y, int health, AnimationComponent renderComponent, List<Vector2> path) {
        super(x, y);
        this.health = health;
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
        addComponent(new ShapeRenderComponent(RenderComponent.Realm.Game, RenderComponent.Layer.Seven, new AaBb(-6, 10, 6, 11), ShapeRenderer.ShapeType.Line, Color.BLACK));
    }

    public void getDamage(int damage){
        health -= damage;
    }
}
