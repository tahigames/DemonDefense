package de.tahigames.demondefense.engine.core.rendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.tahigames.demondefense.engine.core.physics.Bounding;

/**
 * Created by Mirco on 16.04.2015.
 */
public class ShapeRenderComponent extends RenderComponent {

    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Bounding bounding;
    private ShapeRenderer.ShapeType shapeType;
    private Color color;

    public ShapeRenderComponent(Realm realm, Layer layer, Bounding bounding, ShapeRenderer.ShapeType shapeType, Color color) {
        super(realm, layer);
        this.bounding = bounding;
        this.shapeType = shapeType;
        this.color = color;
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(shapeType);
        shapeRenderer.setColor(color);
        bounding.render(shapeRenderer, delta);
        shapeRenderer.end();
        batch.begin();
    }
}
