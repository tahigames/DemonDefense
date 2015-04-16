package de.tahigames.demondefense.game.towers;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.game.TowerAttributes;

/**
 * Created by Marcel on 15.04.2015.
 */
public class BaseTower extends  Tower{

    public BaseTower() {
        super("Basis Turm", new DrawComponent(new Texture("tower01.png"), SIZE, SIZE, DrawComponent.Layer.Five));
    }

    @Override
    protected void generateLevels(TowerAttributes[] levels) {
        levels[0] = new TowerAttributes(64, 100, 1, 200);
        levels[1] = new TowerAttributes(64, 200, 1, 400);
        levels[2] = new TowerAttributes(64, 300, 1, 700);
    }
}
