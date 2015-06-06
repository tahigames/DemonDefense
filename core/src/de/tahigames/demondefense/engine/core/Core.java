package de.tahigames.demondefense.engine.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;

import de.tahigames.demondefense.engine.core.ai.AIEngine;
import de.tahigames.demondefense.engine.core.physics.PhysicsEngine;
import de.tahigames.demondefense.engine.core.rendering.RenderingEngine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Core {

    private Game game;

    private RenderingEngine renderingEngine;
    private PhysicsEngine physicsEngine;
    private AIEngine aiEngine;

    private boolean paused;
    private float speed;

    private FPSLogger fpsLogger;

    public Core(Game game){
        this.game = game;
        renderingEngine = new RenderingEngine();
        physicsEngine = new PhysicsEngine(30);
        aiEngine = new AIEngine(30);

        paused = false;
        speed = 1;

        fpsLogger = new FPSLogger();

        game.init(this);
    }

    public void run(){
        float delta = Gdx.graphics.getDeltaTime();

        delta *= speed;
        if(!paused){
            aiEngine.think(delta);
            physicsEngine.simulate(delta);
        }
        renderingEngine.render(delta);

//        fpsLogger.log();
    }

    public void dispose()
    {
        game.dispose();
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }

    public PhysicsEngine getPhysicsEngine() {
        return physicsEngine;
    }

    public AIEngine getAiEngine() {
        return aiEngine;
    }
}
