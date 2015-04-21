package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Mirco on 21.04.2015.
 */
public class DrawComponent extends RenderComponent {

    private NinePatch ninePatch;
    private Texture texture;

    private float width;
    private float height;

    public DrawComponent(NinePatch ninePatch, float width, float height, Realm realm, Layer layer) {
        super(realm, layer);
        this.ninePatch = ninePatch;
        this.width = width;
        this.height = height;
    }

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
        float x = getParent().getX() - width / 2f;
        float y = getParent().getY() - height / 2f;
        if(ninePatch == null)
            batch.draw(texture, x, y, width, height);
        else
            ninePatch.draw(batch, x, y, width, height);
    }
}
