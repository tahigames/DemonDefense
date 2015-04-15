package de.tahigames.demondefense.game.towers;

import de.tahigames.demondefense.game.TowerAttributes;

/**
 * Created by Marcel on 15.04.2015.
 */
public class BaseTower extends  Tower{

    public BaseTower(float x, float y) {
        super(x, y, "Basis Turm");
    }

    @Override
    protected void generateLevels(TowerAttributes[] levels) {
        levels[0] = new TowerAttributes(100, 100, 1, 200);
        levels[1] = new TowerAttributes(100, 200, 1, 400);
        levels[2] = new TowerAttributes(100, 300, 1, 700);
    }
}
