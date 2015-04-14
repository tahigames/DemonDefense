package de.tahigames.demondefense.game;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.RenderComponent;

/**
 * Created by Marcel on 14.04.2015.
 */
public class Cell extends Entity {

    public Cell(float x, float y) {
        super(x, y);
        addComponent(new RenderComponent(new Texture("testbox16.png")));
    }
}
