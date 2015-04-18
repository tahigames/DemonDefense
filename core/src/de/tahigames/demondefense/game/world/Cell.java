package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.game.world.towers.Tower;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Cell extends Entity {

    public static final int SIZE = 16;

    private MapObject mapObject;
    private boolean blocked;
    private Tower tower;

    public Cell(float x, float y, boolean blocked) {
        super(x, y);
        this.blocked = blocked;
        addComponent(new DrawComponent(new Texture("cells/ground01.png"), SIZE, SIZE, RenderComponent.Realm.Game, DrawComponent.Layer.Nine));
    }

    public void placeTower(Tower tower){
        if(this.tower != null)
            throw new IllegalStateException("Tower already exists");
        this.tower = tower;
        this.tower.getPosition().set(getPosition());
        addChild(this.tower);
        blocked = true;
    }

    public void removeTower(){
        removeChild(tower);
        tower = null;
        blocked = false;
    }

    public boolean isBlocked(){
        return blocked;
    }

}
