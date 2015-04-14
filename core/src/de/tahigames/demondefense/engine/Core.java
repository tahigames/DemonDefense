package de.tahigames.demondefense.engine;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import de.tahigames.demondefense.engine.rendering.RenderComponent;
import de.tahigames.demondefense.engine.rendering.RenderingEngine;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Core {

    private RenderingEngine renderingEngine;

    private Entity root;

    public Core(Game game){
        renderingEngine = new RenderingEngine();

        root = new Entity(0,0);
        root.setCore(this);

        game.init(this);
    }

    public void run(){


        renderingEngine.render();
    }

    public Entity getRoot() {
        return root;
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }
}
