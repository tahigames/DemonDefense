package de.tahigames.demondefense.engine.gui;

import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.rendering.NinePatchComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;

/**
 * Created by Mirco on 22.04.2015.
 */
public class Panel extends Entity {

    private RenderComponent.Layer layer;

    private NinePatchComponent backgroundRenderComponent;
    private float width;
    private float height;

    public Panel(float x, float y, float width, float height, RenderComponent.Layer layer) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.layer = layer;
    }

    @Override
    public void addChild(Entity child) {
        if(child instanceof Panel)
            super.addChild(child);
        else
            throw new IllegalArgumentException("You can only add children that implement GuiComponent!");
    }

    public void tap(float x, float y) {
        if(contains(x, y)){
            if(this instanceof Button){
                ((Button)this).onTap();
            }
            for (int i = 0; i < getChildren().size(); i++){
                ((Panel)getChildren().get(i)).tap(x, y);
            }
        }
    }

    public void setBackground(NinePatch background) {
        if(backgroundRenderComponent == null) {
            backgroundRenderComponent = new NinePatchComponent(background, width, height, RenderComponent.Realm.Gui, layer);
            addComponent(backgroundRenderComponent);
        }
        backgroundRenderComponent.setNinePatch(background);
    }

    public boolean contains(float x, float y) {
        float transX = getTransformedX();
        float transY = getTransformedY();
        if(x > transX - width  / 2f  && x < transX + width / 2f &&
           y > transY - height / 2f && y < transY + height / 2f){
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
