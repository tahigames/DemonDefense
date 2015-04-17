package de.tahigames.demondefense.engine;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Entity {

    private Core core;

    private Entity parent;

    private ArrayList<Entity> entities;
    private ArrayList<Component> components;

    private Vector2 position;

    public Entity(float x, float y) {
        position = new Vector2(x, y);

        //consider lazy initialization?
        entities = new ArrayList<>();
        components = new ArrayList<>();
    }

    public void addChild(Entity child){
        entities.add(child);
        child.setParent(this);
        child.onAddToCore(core);
    }

    public void removeChild(Entity child){
        entities.remove(child);
        child.onRemoveFromCore(core);
        child.setParent(null);
    }

    void setCore(Core core){
        this.core = core;
    }

    public void onAddToCore(Core core){
        this.core = core;

        if(core != null) {
            for (int i = components.size() - 1; i >= 0; i--) {
                components.get(i).onAddToCore(core);
            }
            for (int i = entities.size() - 1; i >= 0; i--) {
                entities.get(i).onAddToCore(core);
            }
        }
    }

    public void onRemoveFromCore(Core core){
        this.core = null;

        if(core != null) {
            for (Component c : components) {
                c.onRemoveFromCore(core);
            }
            for (Entity e : entities) {
                e.onRemoveFromCore(core);
            }
        }
    }

    public void addComponent(Component component) {
        if(component.getParent() != null)
            throw new IllegalArgumentException("You cannot add a component to two entities at once!");

        components.add(component);
        component.setParent(this);
        if(core != null)
            component.onAddToCore(core);
    }

    public void removeComponent(Component component) {
        components.remove(component);
        component.setParent(null);
        if(core != null)
            component.onRemoveFromCore(core);
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public Entity getParent() {
        return parent;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }
}
