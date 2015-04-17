package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.input.GestureDetector;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Game;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.input.GuiGestureListener;
import de.tahigames.demondefense.game.input.MapGestureListener;
import de.tahigames.demondefense.game.world.enemies.Enemy;
import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {
    private Map map;

    @Override
    public void init(Core core) {
        map = new Map(20, 20);
        core.getRoot().addChild(map);
        TextureAtlas atlas = new TextureAtlas(new Texture("enemies/enemy02.png"), 2, 2);
        Enemy enemy = new Enemy(40, 40, 400, new DrawComponent(atlas, 0.25f, Animation.PlayMode.LOOP, RenderComponent.Realm.Game, DrawComponent.Layer.Five));
        map.addChild(enemy);

        GestureDetector mapDetector = new GestureDetector(new MapGestureListener(map, core.getRenderingEngine().getGameCamera()));
        GestureDetector guiDetector = new GestureDetector(new GuiGestureListener(map, core.getRenderingEngine().getGameCamera()));

        InputMultiplexer multiplexer = new InputMultiplexer(guiDetector, mapDetector);
        Gdx.input.setInputProcessor(multiplexer);
    }
}
