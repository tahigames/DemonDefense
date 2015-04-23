package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.Game;
import de.tahigames.demondefense.game.gui.Gui;
import de.tahigames.demondefense.game.input.GuiGestureListener;
import de.tahigames.demondefense.game.input.MapGestureListener;
import de.tahigames.demondefense.game.world.Level;
import de.tahigames.demondefense.game.world.Map;
import de.tahigames.demondefense.game.world.enemies.HellSlime;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {

    @Override
    public void init(Core core) {
        Level level = new Level(core.getRoot(), 0);
        Gui gui = new Gui(level, core.getRenderingEngine().getGuiCamera());

        core.getRoot().addChild(gui);

        GestureDetector mapDetector = new GestureDetector(new MapGestureListener(level.getMap(), gui, core.getRenderingEngine().getGameCamera()));
        GestureDetector guiDetector = new GestureDetector(new GuiGestureListener(gui, core.getRenderingEngine().getGuiCamera()));

        InputMultiplexer multiplexer = new InputMultiplexer(guiDetector, mapDetector);
        Gdx.input.setInputProcessor(multiplexer);
    }
}
