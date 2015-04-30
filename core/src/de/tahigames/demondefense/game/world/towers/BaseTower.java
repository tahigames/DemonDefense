package de.tahigames.demondefense.game.world.towers;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.core.rendering.AnimationComponent;
import de.tahigames.demondefense.engine.core.rendering.DrawComponent;
import de.tahigames.demondefense.engine.core.rendering.RenderComponent;

/**
 * Created by Marcel on 15.04.2015.
 */
public class BaseTower extends  Tower{

    public BaseTower() {
        super("Basis Turm", new DrawComponent(new Texture("towers/tower01.png"), SIZE, SIZE, RenderComponent.Realm.Game, AnimationComponent.Layer.Five));
    }

    @Override
    protected void generateLevels(TowerAttributes[] levels) {
        levels[0] = new TowerAttributes(64, 10, 1, 200);
        levels[1] = new TowerAttributes(64, 20, 1, 400);
        levels[2] = new TowerAttributes(64, 30, 1, 700);
    }
}
