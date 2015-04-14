package de.tahigames.demondefense.game;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Cell extends Entity {
    private Tower tower;

    public Cell(float x, float y) {
        super(x, y);
        addComponent(new RenderComponent(new Texture("testbox16.png")));
    }

    public void placeTower(Tower tower){
        if(this.tower != null)
            throw new IllegalStateException("Tower already exists");
        this.tower = tower;
    }

    public void removeTower(){
        this.tower = null;
    }

    public boolean isBlocked(){
        if (tower != null)
            return true;

        return false;
    }

}
