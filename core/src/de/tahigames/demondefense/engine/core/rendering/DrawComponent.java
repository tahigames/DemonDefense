package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mirco on 21.04.2015.
 */
public class DrawComponent extends RenderComponent {

    private Texture texture;

    private float width;
    private float height;

    public DrawComponent(Texture texture, Realm realm, Layer layer) {
        this(texture, texture.getWidth(), texture.getHeight(), realm, layer);
    }

    public DrawComponent(Texture texture, float width, float height, Realm realm, Layer layer) {
        super(realm, layer);
        this.texture = texture;
        this.width = width;
        this.height = height;
    }


    @Override
    public void render(SpriteBatch batch, float delta) {
        float x = getParent().getTransformedX() - width / 2f;
        float y = getParent().getTransformedY() - height / 2f;
        batch.draw(texture, x, y, width, height);
    }
}
