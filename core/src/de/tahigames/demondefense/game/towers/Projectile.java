package de.tahigames.demondefense.game.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.physics.AaBb;
import de.tahigames.demondefense.engine.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.enemies.Enemy;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Projectile extends Entity{

    private int damage;
    private Enemy target;

    public Projectile(float x, float y) {
        super(x, y);
        TextureAtlas atlas = new TextureAtlas(new Texture("projectile01.png"), 1, 1);
        addComponent(new RenderComponent(atlas, 0, Animation.PlayMode.NORMAL, RenderComponent.Layer.Four));
        PhysicsComponent physicsComp = new PhysicsComponent(new AaBb(getX(), getY(), getX() + atlas.getWidth(), getY() + atlas.getHeight())) {
            @Override
            public boolean canCollideWith(Entity e) {
                return e instanceof Enemy;
            }

            @Override
            public void onCollisionWith(Entity e) {
                if(e == target){
                    ((Enemy) e).getDamage(damage);
                    getParent().getParent().removeChild(getParent());
                }
            }
        };
        addComponent(physicsComp);
        addComponent(new ProjectileAI(physicsComp, target));
    }
}
