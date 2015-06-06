package de.tahigames.demondefense.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.Screen;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.gui.Panel;
import de.tahigames.demondefense.game.input.MapGestureListener;
import de.tahigames.demondefense.game.world.Level;

/**
 * Created by Mirco on 01.05.2015.
 */
public class IngameScreen extends Screen {

    @Override
    protected void initialize(Core core) {
        Level level = new Level(getRoot(), 0);

        Panel sidePanel = new Panel(60, 0, 80, 40, RenderComponent.Layer.Nine);
        getGui().addChild(sidePanel);

        GestureDetector mapDetector = new GestureDetector(new MapGestureListener(level.getMap(), sidePanel.getWidth(), core.getRenderingEngine().getGameCamera()));

        InputMultiplexer multiplexer = new InputMultiplexer(this, mapDetector);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void dispose() {

    }
}
