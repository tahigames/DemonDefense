package de.tahigames.demondefense.engine;

/**
 * Created by Mirco on 14.04.2015.
 */
public abstract class Component {

    private Entity parent;

    public abstract void onAddToCore(Core core);

    public abstract void onRemoveFromCore(Core core);

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public Entity getParent() {
        return parent;
    }
}
