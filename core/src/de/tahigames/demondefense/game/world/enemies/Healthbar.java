package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import de.tahigames.demondefense.engine.core.Entity;
import de.tahigames.demondefense.engine.core.physics.AaBb;
import de.tahigames.demondefense.engine.core.rendering.DrawComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.ShapeRenderComponent;

/**
 * Created by Marcel on 23.04.2015.
 */
public class Healthbar extends Entity{

    private int maxHealth;
    private int size;
    private AaBb healthBox;

    public Healthbar(float x, float y, int maxHealth, int size)
    {
        super(x, y);
        this.maxHealth = maxHealth;
        this.size = size;
        this.healthBox = new AaBb(-size, -size / 10f, size, size / 10f);
        addComponent(new ShapeRenderComponent(RenderComponent.Realm.Game, RenderComponent.Layer.Seven, new AaBb(-6, -1f, 6, 1f), ShapeRenderer.ShapeType.Line, Color.BLACK));
        addComponent(new ShapeRenderComponent(RenderComponent.Realm.Game, RenderComponent.Layer.Seven, healthBox, ShapeRenderer.ShapeType.Line, Color.RED));
    }

    public void setHealth(float health){
        float percentage = MathUtils.clamp(health / maxHealth, 0, 1);
        healthBox.getMaxExtent().x = (percentage * size * 2) - size;
    }

}
