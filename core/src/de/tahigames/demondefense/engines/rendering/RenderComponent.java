package de.tahigames.demondefense.engines.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.engines.Component;
import de.tahigames.demondefense.Core;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderComponent extends Component {

    private Texture texture;

    public RenderComponent(Texture texture) {
        this.texture = texture;
    }

    public void render(SpriteBatch batch){
        float x = getParent().getX();
        float y = getParent().getY();
        batch.draw(texture, x, y);
    }

    @Override
    public void onAddToCore(Core core) {
        core.getRenderingEngine().addComponent(this);
    }

    @Override
    public void onRemoveFromCore(Core core) {
        core.getRenderingEngine().removeComponent(this);
    }
}
