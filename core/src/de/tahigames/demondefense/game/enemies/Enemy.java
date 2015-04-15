package de.tahigames.demondefense.game.enemies;

import com.badlogic.gdx.graphics.g2d.Animation;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.physics.AaBb;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.towers.Projectile;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Enemy extends Entity {

    private int health;

    public Enemy(float x, float y, int health, TextureAtlas atlas, float frameTime) {
        super(x, y);
        this.health = health;
        addComponent(new RenderComponent(atlas, frameTime, Animation.PlayMode.LOOP, RenderComponent.Layer.Five));
        float halfWidth = atlas.getWidth() / 2;
        float halfHeight = atlas.getHeight() / 2;
        addComponent(new PhysicsComponent(new AaBb(getX() - halfWidth, getY() - halfHeight, getX() + halfWidth, getY() + halfHeight)) {
            @Override
            public boolean canCollideWith(Entity e) {
                return e instanceof Projectile;
            }
        });
    }

    public void getDamage(int damage){
        health -= damage;
        if(health<=0)
            destroy();
    }

    private void destroy(){
        //explodier animation und remove
    }
}
