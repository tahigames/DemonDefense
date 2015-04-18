package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Game;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.gui.Gui;
import de.tahigames.demondefense.game.input.GuiGestureListener;
import de.tahigames.demondefense.game.input.MapGestureListener;
import de.tahigames.demondefense.game.world.enemies.Enemy;
import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {

    @Override
    public void init(Core core) {
//        Map map = new Map(20, 20);
//        Gui gui = new Gui(map);
//
//        core.getRoot().addChild(gui);
//        core.getRoot().addChild(map);
//
//        TextureAtlas atlas = new TextureAtlas(new Texture("enemies/enemy02.png"), 2, 2);
//        Enemy enemy = new Enemy(40, 40, 400, new DrawComponent(atlas, 0.25f, Animation.PlayMode.LOOP, RenderComponent.Realm.Game, DrawComponent.Layer.Five));
//        map.addChild(enemy);
//
//        GestureDetector mapDetector = new GestureDetector(new MapGestureListener(map, core.getRenderingEngine().getGameCamera()));
//        GestureDetector guiDetector = new GestureDetector(new GuiGestureListener(gui, core.getRenderingEngine().getGuiCamera()));
//
//        InputMultiplexer multiplexer = new InputMultiplexer(guiDetector, mapDetector);
//        Gdx.input.setInputProcessor(multiplexer);

        TiledMap tmap = new TmxMapLoader().load("levels/wasteland.tmx");
        float unitScale = 1 / 16f;
        OrthogonalTiledMapRenderer renderer = new OrthogonalTiledMapRenderer(tmap, unitScale);



    }
}
