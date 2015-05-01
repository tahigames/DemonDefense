package de.tahigames.demondefense.engine.core;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Entity {

    private Core core;

    private Entity parent;

    private ArrayList<Entity> children;
    private ArrayList<Component> components;

    private Vector2 position;

    public Entity(float x, float y) {
        position = new Vector2(x, y);

        //consider lazy initialization?
        children = new ArrayList<>();
        components = new ArrayList<>();
    }

    public void addChild(Entity child){
        children.add(child);
        child.setParent(this);
        child.onAddToCore(core);
    }

    public void removeChild(Entity child){
        children.remove(child);
        child.onRemoveFromCore(core);
        child.setParent(null);
    }

    public void removeAllChildren() {
        while(children.size() > 0)
            removeChild(children.get(children.size() - 1));
    }

    public void setCore(Core core){
        this.core = core;
    }

    public void onAddToCore(Core core){
        this.core = core;

        if(core != null) {
            for (int i = components.size() - 1; i >= 0; i--) {
                components.get(i).onAddToCore(core);
            }
            for (int i = children.size() - 1; i >= 0; i--) {
                children.get(i).onAddToCore(core);
            }
        }
    }

    public void onRemoveFromCore(Core core){
        this.core = null;

        if(core != null) {
            for (Component c : components) {
                c.onRemoveFromCore(core);
            }
            for (Entity e : children) {
                e.onRemoveFromCore(core);
            }
        }
    }

    public void addComponent(Component component) {
        if(component.getParent() != null)
            throw new IllegalArgumentException("You cannot add a component to two  at once!");

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

    public ArrayList<Entity> getChildren() {
        return children;
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

    public float getTransformedX() {
        float transformedX = getX();
        if(parent != null)
            transformedX += parent.getTransformedX();
        return transformedX;
    }

    public float getTransformedY() {
        float transformedY = getY();
        if(parent != null)
            transformedY += parent.getTransformedY();
        return transformedY;
    }
}
