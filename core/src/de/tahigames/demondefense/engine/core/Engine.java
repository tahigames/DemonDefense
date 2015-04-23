package de.tahigames.demondefense.engine.core;

import java.util.ArrayList;

/**
 * Created by Mirco on 14.04.2015.
 */
public class Engine<T extends Component> {

    private ArrayList<T> components;

    public Engine(){
        components = new ArrayList<>();
    }


    public void addComponent(T component){
        components.add(component);
    }

    public void removeComponent(T component){
        components.remove(component);
    }

    public ArrayList<T> getComponents() {
        return components;
    }
}
