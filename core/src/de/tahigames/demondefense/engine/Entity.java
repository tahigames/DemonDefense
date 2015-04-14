package de.tahigames.demondefense.engine;

import java.util.ArrayList;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Entity {

    private Core core;

    private ArrayList<Component> components;

    private float x, y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;

        //consider lazy initialization?
        components = new ArrayList<>();
    }

    public void onAddToCore(Core core){
        this.core = core;

        if(core != null)
            for (Component c : components){
                c.onAddToCore(core);
            }
    }

    public void onRemoveFromCore(Core core){
        this.core = null;

        if(core != null)
            for (Component c : components){
                c.onAddToCore(core);
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
