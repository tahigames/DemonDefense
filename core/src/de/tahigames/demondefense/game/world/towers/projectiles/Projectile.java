package de.tahigames.demondefense.game.world.towers.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.physics.AaBb;
import de.tahigames.demondefense.engine.core.physics.PhysicsComponent;
import de.tahigames.demondefense.engine.core.rendering.AnimationComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.TextureAtlas;
import de.tahigames.demondefense.game.world.enemies.Enemy;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Projectile extends Entity{

    private int damage;

    public Projectile(float x, float y, final Enemy target) {
        super(x, y);
        TextureAtlas atlas = new TextureAtlas(new Texture("towers/projectiles/projectile01.png"), 1, 1);
        addComponent(new AnimationComponent(atlas, 0, Animation.PlayMode.NORMAL, RenderComponent.Realm.Game, RenderComponent.Layer.Four));
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
