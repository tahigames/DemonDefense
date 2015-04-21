package de.tahigames.demondefense.game.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.AnimationComponent;
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
        super((cam.viewportWidth * (2f/3f) * cam.zoom) / 2f, 0);
        this.map = map;
        this.width = cam.viewportWidth / 3f * cam.zoom;
        this.height = cam.viewportHeight * cam.zoom;
        addComponent(new DrawComponent(new NinePatch(new Texture("gui/gui01.png"), 5, 5, 5, 5), width , height, RenderComponent.Realm.Gui, RenderComponent.Layer.Nine));
    }

    public boolean tap(float x, float y){
        if(x >= this.getX() - (width / 2)) {
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
