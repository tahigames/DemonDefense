package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Core;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderComponent extends Component implements Comparable<RenderComponent> {

    private Texture texture;
    private float width, height;
    private float depth;

    public RenderComponent(Texture texture, float depth) {
        this(texture, texture.getWidth(), texture.getHeight(), depth);
    }

    public RenderComponent(Texture texture, float width, float height, float depth){
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.depth = depth;
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
        return depth > o.depth ? 1 : depth < o.depth ? - 1 : 0;
    }
}
