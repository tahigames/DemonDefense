package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Core;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderComponent extends Component implements Comparable<RenderComponent> {

    public static  enum Layer {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    }

    private Texture texture;
    private float width, height;
    private Layer layer;

    public RenderComponent(Texture texture, Layer layer) {
        this(texture, texture.getWidth(), texture.getHeight(), layer);
    }

    public RenderComponent(Texture texture, float width, float height, Layer layer){
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.layer = layer;
    }

    public void render(SpriteBatch batch){
        float x = getParent().getX();
        float y = getParent().getY();
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void onAddToCore(Core core) {
        core.getRenderingEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        core.getRenderingEngine().removeComponent(this);
    }

    @Override
    public int compareTo(RenderComponent o) {
        return layer.compareTo(o.layer);
    }
}
