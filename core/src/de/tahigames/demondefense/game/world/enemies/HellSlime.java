package de.tahigames.demondefense.game.world.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Queue;

import de.tahigames.demondefense.engine.core.rendering.AnimationComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.core.rendering.TextureAtlas;

/**
 * Created by Mirco on 20.04.2015.
 */
public class HellSlime extends Enemy{

    //private static final AnimationComponent drawComponent = new AnimationComponent(new TextureAtlas(new Texture("enemies/enemy02.png"), 2, 2), 0.25f, Animation.PlayMode.LOOP, RenderComponent.Realm.Game, RenderComponent.Layer.Eight);

    public HellSlime(float x, float y, List<Vector2> path) {
        super(x, y, 100, new AnimationComponent(new TextureAtlas(new Texture("enemies/enemy02.png"), 2, 2), 0.25f, Animation.PlayMode.LOOP, RenderComponent.Realm.Game, RenderComponent.Layer.Eight), path);
    }
}
