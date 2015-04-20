package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Collections;
import java.util.List;

import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderingEngine extends Engine<RenderComponent> {

    private Viewport viewPort;
    private OrthographicCamera gameCamera;
    private OrthographicCamera guiCamera;

    private SpriteBatch batch;

    public RenderingEngine(){
        gameCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        gameCamera.zoom = 0.25f;
        viewPort = new FitViewport(640, 480, gameCamera);
        viewPort.apply();
        guiCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameCamera.update();

        List<RenderComponent> components = getComponents();

        //TODO don't sort everytime when rendering... put every new component into the right position when it is being added.
        Collections.sort(components);

        batch.begin();
        batch.setProjectionMatrix(gameCamera.combined);

        int i = 0;
        RenderComponent c;
        while(i < components.size() && (c = components.get(i)).getRealm() == RenderComponent.Realm.Game){
            c.render(batch, delta);
            i++;
        }

        batch.setProjectionMatrix(guiCamera.combined);
        while(i < components.size() && (c = components.get(i)).getRealm() == RenderComponent.Realm.Gui){
            c.render(batch, delta);
            i++;
        }
        batch.end();

    }

    public OrthographicCamera getGameCamera() {
        return gameCamera;
    }

    public OrthographicCamera getGuiCamera() {
        return guiCamera;
    }
}
