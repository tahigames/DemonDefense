package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mirco on 22.04.2015.
 */
public class NinePatchComponent extends RenderComponent {

    private NinePatch ninePatch;
    private float width, height;

    public NinePatchComponent (NinePatch ninePatch, float width, float height, Realm realm, Layer layer) {
        super(realm, layer);
        this.ninePatch = ninePatch;
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        float x = getParent().getTransformedX() - width / 2f;
        float y = getParent().getTransformedY() - height / 2f;
        ninePatch.draw(batch, x, y, width, height);
    }

    public void setNinePatch(NinePatch ninePatch) {
        this.ninePatch = ninePatch;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
