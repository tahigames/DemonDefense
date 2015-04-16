package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.engine.Component;
import de.tahigames.demondefense.engine.Core;

/**
 * Created by Mirco on 16.04.2015.
 */
public abstract class RenderComponent extends Component implements Comparable<RenderComponent> {

    public static  enum Layer {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine;
    }

    private Layer layer;

    public RenderComponent(Layer layer){
        this.layer = layer;
    }

    public abstract void render(SpriteBatch batch, float delta);

    @Override
    public void onAddToCore(Core core) {
        super.onAddToCore(core);
        core.getRenderingEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        super.onRemoveFromCore(core);
        core.getRenderingEngine().removeComponent(this);
    }

    @Override
    public int compareTo(RenderComponent o) {
        return -layer.compareTo(o.layer);
    }

    public Layer getLayer() {
        return layer;
    }
}
