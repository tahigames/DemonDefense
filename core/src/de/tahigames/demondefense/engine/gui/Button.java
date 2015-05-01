package de.tahigames.demondefense.engine.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.TextRenderComponent;

/**
 * Created by Mirco on 22.04.2015.
 */
public abstract class Button extends Panel {

    private BitmapFont font;

    public Button(float x, float y, float width, float height, String text, NinePatch background, RenderComponent.Layer layer) {
        super(x, y, width, height, layer);
        setBackground(background);
        addComponent(new TextRenderComponent(text, RenderComponent.Realm.Gui, RenderComponent.Layer.One));
    }

    protected abstract void onTap();

}
