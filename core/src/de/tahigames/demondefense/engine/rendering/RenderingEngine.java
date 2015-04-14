package de.tahigames.demondefense.engine.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.tahigames.demondefense.engine.Engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class RenderingEngine extends Engine<RenderComponent> {

    private SpriteBatch batch;

    public RenderingEngine(){
        batch = new SpriteBatch();
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (RenderComponent c : getComponents()){
            c.render(batch);
        }
        batch.end();
    }


}
