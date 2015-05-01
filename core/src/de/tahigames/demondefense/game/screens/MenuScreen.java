package de.tahigames.demondefense.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

import de.tahigames.demondefense.engine.core.Core;
import de.tahigames.demondefense.engine.core.Screen;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.gui.Button;
import de.tahigames.demondefense.engine.gui.Panel;

/**
 * Created by Mirco on 23.04.2015.
 */
public class MenuScreen extends Screen {

    private static final float BACKGROUND_PANEL_WIDTH = 40;
    private static final float BACKGROUND_PANEL_HEIGHT = 80;

    @Override
    public void initialize(Core core) {

        Panel backgroundPanel = new Panel(60, 0, 40, 80, RenderComponent.Layer.Nine);
        backgroundPanel.setBackground(new NinePatch(new Texture("gui/gui01.png"), 7, 0, 7, 7));

        backgroundPanel.addChild(new Button(0, 24, 32, 16, "Start", new NinePatch(new Texture("gui/button01.png"), 6, 6, 7, 7), RenderComponent.Layer.Eight) {
            @Override
            protected void onTap() {
                game.setScreen(new IngameScreen());
            }
        });
        backgroundPanel.addChild(new Button(0, 0, 32, 16, "Options", new NinePatch(new Texture("gui/button01.png"), 6, 6, 7, 7), RenderComponent.Layer.Eight) {
            @Override
            protected void onTap() {
            }
        });
        backgroundPanel.addChild(new Button(0, -24, 32, 16, "End", new NinePatch(new Texture("gui/button01.png"), 6, 6, 7, 7), RenderComponent.Layer.Eight) {
            @Override
            protected void onTap() {
            }
        });

        getGui().addChild(backgroundPanel);
    }

    @Override
    public void dispose() {

    }
}
