package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Mirco on 17.04.2015.
 */
public class GameInputProcessor implements InputProcessor{

    private Map map;
    private Camera cam;

    public GameInputProcessor(Map map, Camera cam){
        this.map = map;
        this.cam = cam;
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
        Vector3 screenCoord = new Vector3(screenX, screenY, 0);
        Vector3 worldCoord = cam.unproject(screenCoord);
        map.selectCellAt(worldCoord.x, worldCoord.y);
        map.placeTower();
        return true;
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
