package de.tahigames.demondefense.game.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.rendering.NinePatchComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.game.world.Level;
import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Marcel on 17.04.2015.
 */
public class Gui extends Entity {
    private float width;
    private float height;
    private Map map;

    public Gui(Level level, OrthographicCamera cam) {
        super((cam.viewportWidth * (2f/3f) * cam.zoom) / 2f, 0);
        this.map = level.getMap();
        this.width = cam.viewportWidth / 3f;
        this.height = cam.viewportHeight;
        addComponent(new NinePatchComponent(new NinePatch(new Texture("gui/gui01.png"), 7, 0, 7, 7), width * cam.zoom, height * cam.zoom, RenderComponent.Realm.Gui, RenderComponent.Layer.Nine));
        addChild(new Button(getX(), getY()));
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
