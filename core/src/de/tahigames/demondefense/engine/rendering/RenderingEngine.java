package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderingEngine extends Engine<RenderComponent> {

    private OrthographicCamera camera;

    private SpriteBatch batch;

    public RenderingEngine(){
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        batch = new SpriteBatch();
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        for (RenderComponent c : getComponents()){
            c.render(batch);
        }
        batch.end();
    }

    //Temporary
    private void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, -3, 0);
        }

//        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100 / camera.viewportWidth);


    }


}
