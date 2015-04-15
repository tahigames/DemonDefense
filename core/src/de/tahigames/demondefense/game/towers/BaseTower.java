package de.tahigames.demondefense.game.towers;

import com.badlogic.gdx.graphics.Texture;

import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.TowerAttributes;

/**
 * Created by Marcel on 15.04.2015.
 */
public class BaseTower extends  Tower{

    public BaseTower() {
        super("Basis Turm", new TextureAtlas(new Texture("tower01.png"), 1, 1), 0);
    }

    @Override
    protected void generateLevels(TowerAttributes[] levels) {
        levels[0] = new TowerAttributes(32, 100, 0.25f, 200);
        levels[1] = new TowerAttributes(32, 200, 1, 400);
        levels[2] = new TowerAttributes(32, 300, 1, 700);
    }
}
