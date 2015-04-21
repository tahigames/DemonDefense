package de.tahigames.demondefense.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;

/**
 * Created by Marcel on 21.04.2015.
 */
public class Button extends Entity {


    public Button(float x, float y) {
        super(x, y);
        addComponent(new DrawComponent(new NinePatch(new Texture("gui/button01.png"), 6, 6, 7, 7), 40 , 20, RenderComponent.Realm.Gui, RenderComponent.Layer.Eight));
    }

}
