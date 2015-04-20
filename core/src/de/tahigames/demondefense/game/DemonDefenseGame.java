package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.input.GestureDetector;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Game;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.game.gui.Gui;
import de.tahigames.demondefense.game.input.GuiGestureListener;
import de.tahigames.demondefense.game.input.MapGestureListener;
import de.tahigames.demondefense.game.world.Map;
import de.tahigames.demondefense.game.world.enemies.Enemy;
import de.tahigames.demondefense.game.world.enemies.HellSlime;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {

    @Override
    public void init(Core core) {
        Map map = new Map("wasteland.tmx");
        Gui gui = new Gui(map, core.getRenderingEngine().getGuiCamera());

        core.getRoot().addChild(gui);
        core.getRoot().addChild(map);

        HellSlime slime = new HellSlime(0, 0);
        map.addChild(slime);

        GestureDetector mapDetector = new GestureDetector(new MapGestureListener(map, gui, core.getRenderingEngine().getGameCamera()));
        GestureDetector guiDetector = new GestureDetector(new GuiGestureListener(gui, core.getRenderingEngine().getGuiCamera()));

        InputMultiplexer multiplexer = new InputMultiplexer(guiDetector, mapDetector);
        Gdx.input.setInputProcessor(multiplexer);
    }
}
