package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.game.world.towers.Tower;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Cell {

    public static final int SIZE = 16;

    private boolean blocked;
    private boolean forConstruction;

    public Cell(boolean blocked, boolean forConstruction) {
        this.blocked = blocked;
        this.forConstruction = forConstruction;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public boolean isForConstruction(){
        return !blocked && forConstruction;
    }

}
