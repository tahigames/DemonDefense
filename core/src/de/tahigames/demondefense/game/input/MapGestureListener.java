package de.tahigames.demondefense.game.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Marcel on 17.04.2015.
 */
public class MapGestureListener implements GestureDetector.GestureListener {

    private OrthographicCamera cam;
    private Map map;
    private float initialScale;

    public MapGestureListener(Map map, OrthographicCamera cam){
        this.map = map;
        this.cam = cam;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        initialScale = cam.zoom;
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector3 worldCoord = cam.unproject(new Vector3(x, y, 0));
        map.selectCellAt(worldCoord.x, worldCoord.y);
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        cam.translate(-deltaX * cam.zoom, deltaY * cam.zoom);
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        cam.zoom = initialScale * (initialDistance / distance);
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
