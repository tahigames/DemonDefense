package de.tahigames.demondefense.engine.core;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import de.tahigames.demondefense.engine.core.rendering.RenderComponent;
import de.tahigames.demondefense.engine.gui.Panel;

/**
 * Created by Mirco on 23.04.2015.
 */
public abstract class Screen implements InputProcessor {

    protected Game game;

    private Entity root;
    private Panel gui;

    public Screen() {
        root = new Entity(0, 0);
        gui = new Panel(0, 0, Float.MAX_VALUE, Float.MAX_VALUE, RenderComponent.Layer.Nine);
    }

    void start(Game game, Core core){
        this.game = game;
        root.setCore(core);
        gui.setCore(core);

        initialize(core);
    }

    protected abstract void initialize(Core core);

    void end() {
        root.removeAllChildren();
        gui.removeAllChildren();

        dispose();
    }

    public abstract void dispose();

    public Entity getRoot() {
        return root;
    }

    public Panel getGui() {
        return gui;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 coords = new Vector3(screenX, screenY, 0);
        game.getCore().getRenderingEngine().getGuiCamera().unproject(coords);
        gui.tap(coords.x, coords.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
