package de.tahigames.demondefense.game.input;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import de.tahigames.demondefense.game.gui.Gui;
import de.tahigames.demondefense.game.world.Map;

/**
 * Created by Marcel on 17.04.2015.
 */
public class MapGestureListener implements GestureDetector.GestureListener {

    private OrthographicCamera cam;
    private Map map;
    private Gui gui;
    private float initialScale;

    public MapGestureListener(Map map, Gui gui, OrthographicCamera cam){
        this.map = map;
        this.gui = gui;
        this.cam = cam;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        initialScale = cam.zoom;
        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        correctOverlap();

        Vector3 worldCoord = cam.unproject(new Vector3(x, y, 0));
        map.select(worldCoord.x, worldCoord.y);
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
        float moveX = -deltaX * cam.zoom;
        float moveY = deltaY * cam.zoom;
        cam.translate(moveX, moveY);

        //move camera position if out of mapRange
        correctOverlap();

        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        cam.zoom = initialScale * (initialDistance / distance);

        final float viewWidth = cam.viewportWidth - gui.getWidth();
        final float viewHeight = cam.viewportHeight;

        //clamp zoom to map
        final float maxZoomX = map.getWidth() / viewWidth;
        final float maxZoomY = map.getHeight() / viewHeight;
        final float minZoom = 0.2f;
        final float maxZoom = Math.min(maxZoomX, maxZoomY);
        cam.zoom = MathUtils.clamp(cam.zoom, minZoom, maxZoom);
        System.out.println(cam.zoom);

        //move camera position if out of mapRange
        correctOverlap();

        return true;
    }

    private void correctOverlap(){
        final float viewWidth = cam.viewportWidth - gui.getWidth();
        final float viewHeight = cam.viewportHeight;

        Vector3 camMinExtent = new Vector3(cam.position.x - (cam.viewportWidth / 2f) * cam.zoom, cam.position.y - (viewHeight / 2f) * cam.zoom, 0);
        Vector3 camMaxExtent = new Vector3(cam.position.x + (viewWidth / 2f) * cam.zoom, cam.position.y + (viewHeight / 2f) * cam.zoom, 0);

        Vector3 minDist = new Vector3();
        minDist.set(map.getPosition(), 0);
        minDist.sub(map.getWidth() / 2f, map.getHeight() / 2f, 0f);
        minDist.sub(camMinExtent);

        minDist.set(Math.max(0, minDist.x), Math.max(0, minDist.y), 0);

        Vector3 maxDist = new Vector3();
        maxDist.set(map.getPosition(), 0);
        maxDist.add(map.getWidth() / 2f, map.getHeight() / 2f, 0f);
        maxDist.sub(camMaxExtent);

        maxDist.set(Math.min(0, maxDist.x), Math.min(0, maxDist.y), 0);

        float xCorrection = maxDist.x + minDist.x;
        float yCorrection = maxDist.y + minDist.y;

        cam.translate(xCorrection, yCorrection);
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }
}
