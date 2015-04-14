package de.tahigames.demondefense.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import de.tahigames.demondefense.engine.physics.PhysicsEngine;
import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.RenderingEngine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Core {

    private Game game;

    private RenderingEngine renderingEngine;
    private PhysicsEngine physicsEngine;

    private Entity root;

    public Core(Game game){
        this.game = game;
        renderingEngine = new RenderingEngine();
        physicsEngine = new PhysicsEngine();

        root = new Entity(0,0);
        root.setCore(this);

        game.init(this);
    }

    public void run(){
        float delta = Gdx.graphics.getDeltaTime();

        game.handleInput(Gdx.input);
        physicsEngine.simulate(delta);
        renderingEngine.render();
    }

    public Entity getRoot() {
        return root;
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }

    public PhysicsEngine getPhysicsEngine() {
        return physicsEngine;
    }
}
