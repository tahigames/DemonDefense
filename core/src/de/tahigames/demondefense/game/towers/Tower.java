package de.tahigames.demondefense.game.towers;

import de.tahigames.demondefense.engine.Entity;
import de.tahigames.demondefense.game.TowerAttributes;

/**
 * Created by Marcel on 14.04.2015.
 */
public abstract class Tower extends Entity{

    private String name;
    private int level;
    private TowerAttributes[] levels;

    public Tower(float x, float y, String name) {
        super(x, y);
        this.name = name;
        level = 0;
        levels = new TowerAttributes[3];
        generateLevels(levels);
    }

    protected abstract void generateLevels(TowerAttributes[] levels);

    public void upgrade(){
        level++;
    }

    public TowerAttributes getAttributes(int level){
        return levels[level];
    }

    public String getName(){
        return name;
    }

    public int getLevel(){
        return level;
    }

    private void shoot(){
        //projektil erstellen addChild
    }

}
