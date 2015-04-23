package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.physics.AaBb;
import de.tahigames.demondefense.engine.core.rendering.DrawComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.ShapeRenderComponent;

/**
 * Created by Marcel on 23.04.2015.
 */
public class Healthbar extends Entity{

    public Healthbar(float x, float y) {
        super(x, y);
        addComponent(new ShapeRenderComponent(RenderComponent.Realm.Game, RenderComponent.Layer.Seven, new AaBb(-6, -1, 6, 2), ShapeRenderer.ShapeType.Line, Color.BLACK));
        addComponent(new DrawComponent(new Texture("enemies/healthbar.png"), 12, 1, RenderComponent.Realm.Game, RenderComponent.Layer.Seven));
    }

}
