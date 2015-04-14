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

    private ArrayList<Entity> entities;

    public Core(){
        renderingEngine = new RenderingEngine();

        entities = new ArrayList<>();

        Entity e = new Entity(50,50);
        e.addComponent(new RenderComponent(new Texture("badlogic.jpg")));
        addEntity(e);
    }

    public void run(){


        renderingEngine.render();
    }

    public void addEntity(Entity e){
        entities.add(e);
        e.onAddToCore(this);
    }

    public void removeEntity(Entity e){
        entities.remove(e);
        e.onRemoveFromCore(this);
    }

    public RenderingEngine getRenderingEngine() {
        return renderingEngine;
    }
}
