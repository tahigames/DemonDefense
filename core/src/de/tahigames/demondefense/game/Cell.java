package de.tahigames.demondefense.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.towers.Tower;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Cell extends Entity {

    public static final int SIZE = 16;

    private Tower tower;

    public Cell(float x, float y) {
        super(x, y);
        addComponent(new RenderComponent(new Texture("ground01.png"), SIZE, SIZE, RenderComponent.Layer.Nine));
    }

    public void placeTower(Tower tower){
        if(this.tower != null)
            throw new IllegalStateException("Tower already exists");
        this.tower = tower;
        this.tower.getPosition().set(getPosition());
        addChild(this.tower);
    }

    public void removeTower(){
        removeChild(tower);
        tower = null;
    }

    public boolean isBlocked(){
        if (tower != null)
            return true;

        return false;
    }

}
