package de.tahigames.demondefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.Screen;
import de.tahigames.demondefense.game.gui.Gui;
import de.tahigames.demondefense.game.input.GuiGestureListener;
import de.tahigames.demondefense.game.input.MapGestureListener;
import de.tahigames.demondefense.game.world.Level;

/**
 * Created by Mirco on 01.05.2015.
 */
public class IngameScreen extends Screen {

    @Override
    protected void initialize(Core core) {
        Level level = new Level(core.getRoot(), 0);
        Gui gui = new Gui(level, core.getRenderingEngine().getGuiCamera());

        core.getRoot().addChild(gui);

        GestureDetector mapDetector = new GestureDetector(new MapGestureListener(level.getMap(), gui, core.getRenderingEngine().getGameCamera()));
        GestureDetector guiDetector = new GestureDetector(new GuiGestureListener(gui, core.getRenderingEngine().getGuiCamera()));

        InputMultiplexer multiplexer = new InputMultiplexer(this, guiDetector, mapDetector);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void dispose() {

    }
}
