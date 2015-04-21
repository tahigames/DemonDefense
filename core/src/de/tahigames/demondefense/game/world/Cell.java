package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.game.world.towers.Tower;

/**
 * Created by Marcel on 21.04.2015.
 */
public class Cell extends Entity{

    private static RenderComponent selectRenderer = new DrawComponent(new Texture("cells/selector.png") , Map.CELL_SIZE, Map.CELL_SIZE, RenderComponent.Realm.Game, RenderComponent.Layer.Eight);

    private Tower tower;
    private boolean blocked;
    private boolean forConstruction;

    private boolean blockingPath;

    public Cell(float x , float y, boolean blocked, boolean forConstruction) {
        super(x, y);
        this.blocked = blocked;
        this.forConstruction = forConstruction;
    }

    public void placeTower(Tower tower){
        if(tower != null){
            throw new IllegalStateException("Tower already exists");
        }
        this.tower = tower;
        addChild(this.tower);
        this.tower.getPosition().set(getPosition());
        deselect();
        select();
    }

    public void removeTower(){
        removeChild(tower);
        this.tower = null;
        deselect();
        select();
    }

    public void select(){
        if(tower != null){
            tower.select();
        } else {
            addComponent(selectRenderer);
        }
    }

    public void deselect(){
        if(tower != null){
            tower.deselect();
        } else {
            removeComponent(selectRenderer);
        }
    }

    public void setBlockingPath(boolean blockingPath) {
        this.blockingPath = blockingPath;
    }

    public boolean isBlocked(){
        return blocked || this.tower != null || blockingPath;
    }

    public boolean isSelectable(){
        return !blocked && forConstruction;
    }

}
