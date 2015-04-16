package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.tahigames.demondefense.engine.physics.Bounding;

/**
 * Created by Mirco on 16.04.2015.
 */
public class ShapeRenderComponent extends RenderComponent {

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Bounding bounding;

    public ShapeRenderComponent(Layer layer, Bounding bounding) {
        super(layer);
        this.bounding = bounding;
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        bounding.render(shapeRenderer, delta);
        shapeRenderer.end();
        batch.begin();
    }
}
