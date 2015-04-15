package de.tahigames.demondefense.game.enemies;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;

/**
 * Created by Marcel on 15.04.2015.
 */
public class Enemy extends Entity {

    private int health;

    public Enemy(float x, float y, int health, TextureAtlas atlas, float frameTime) {
        super(x, y);
        this.health = health;
        addComponent(new RenderComponent(atlas, frameTime, RenderComponent.Layer.Five));
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
