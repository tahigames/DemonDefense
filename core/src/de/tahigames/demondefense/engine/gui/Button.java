package de.tahigames.demondefense.engine.gui;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.core.rendering.RenderComponent;

/**
 * Created by Mirco on 22.04.2015.
 */
public abstract class Button extends Panel {

    public Button(float x, float y, float width, float height, NinePatch background, RenderComponent.Layer layer) {
        super(x, y, width, height, layer);
        setBackground(background);
    }

    protected abstract void onTap();

}
