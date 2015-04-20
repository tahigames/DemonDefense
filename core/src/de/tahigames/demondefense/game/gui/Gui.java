package de.tahigames.demondefense.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Marcel on 17.04.2015.
 */
public class Gui extends Entity {
    private float width;
    private float height;
    private Map map;

    public Gui(Map map, OrthographicCamera cam) {
        super((cam.viewportWidth * (3f/4f)) / 2f, 0);
        this.map = map;
        this.width = cam.viewportWidth / 4f;
        this.height = cam.viewportHeight;
        addComponent(new DrawComponent(new Texture("gui/gui01.png"), width , height, RenderComponent.Realm.Gui, RenderComponent.Layer.Nine));
    }

    public boolean tap(float x, float y){
        if(x >= this.getX() - (width / 2)){
            map.placeTower();
            return true;
        }
        return false;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
