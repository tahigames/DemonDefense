package de.tahigames.demondefense.game.world.towers;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.RenderComponent;

/**
 * Created by Marcel on 15.04.2015.
 */
public class BaseTower extends  Tower{

    public BaseTower() {
        super("Basis Turm", new DrawComponent(new Texture("towers/tower01.png"), SIZE, SIZE, RenderComponent.Realm.Game, DrawComponent.Layer.Five));
    }

    @Override
    protected void generateLevels(TowerAttributes[] levels) {
        levels[0] = new TowerAttributes(64, 100, 1, 200);
        levels[1] = new TowerAttributes(64, 200, 1, 400);
        levels[2] = new TowerAttributes(64, 300, 1, 700);
    }
}
