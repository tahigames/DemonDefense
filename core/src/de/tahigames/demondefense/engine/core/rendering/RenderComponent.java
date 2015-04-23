package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.engine.core.Component;
import de.tahigames.demondefense.engine.core.Core;

/**
 * Created by Mirco on 16.04.2015.
 */
public abstract class RenderComponent extends Component implements Comparable<RenderComponent> {

    public static enum Realm {
        Game, Gui
    }

    public static  enum Layer {
        Nine, Eight, Seven, Six, Five, Four, Three, Two, One, Zero;
    }

    private Realm realm;
    private Layer layer;

    public RenderComponent(Realm realm, Layer layer){
        this.realm = realm;
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
        int result = realm.compareTo(o.realm);
        if(result != 0)
            return result;
        return layer.compareTo(o.layer);
    }

    public Realm getRealm() {
        return realm;
    }

    public Layer getLayer() {
        return layer;
    }
}
