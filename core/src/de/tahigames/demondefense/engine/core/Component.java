package de.tahigames.demondefense.engine.core;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class Component {

    private Entity parent;

    private boolean connectedToCore;

    public void onAddToCore(Core core){
        connectedToCore = true;
    }

    public void onRemoveFromCore(Core core){
        connectedToCore = false;
    }

    public boolean isConnectedToCore() {
        return connectedToCore;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public Entity getParent() {
        return parent;
    }
}
