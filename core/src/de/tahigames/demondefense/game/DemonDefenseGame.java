package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector3;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Game;
import de.tahigames.demondefense.engine.rendering.DrawComponent;
import de.tahigames.demondefense.engine.rendering.TextureAtlas;
import de.tahigames.demondefense.game.enemies.Enemy;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {
    private Map map;

    @Override
    public void init(Core core) {
        map = new Map(20, 20);
        core.getRoot().addChild(map);
        TextureAtlas atlas = new TextureAtlas(new Texture("enemy02.png"), 2, 2);
        Enemy enemy = new Enemy(40, 40, 400, new DrawComponent(atlas, 0.25f, Animation.PlayMode.LOOP, DrawComponent.Layer.Five));
        map.addChild(enemy);
    }

    @Override
    public void handleInput(Input input, Camera cam){
        if(Gdx.input.justTouched()){
            Vector3 screenCoord = new Vector3(input.getX(), input.getY(), 0);
            Vector3 worldCoord = cam.unproject(screenCoord);
            map.selectCellAt(worldCoord.x, worldCoord.y);
            map.placeTower();
            Gdx.app.log("Input", "X: " + input.getX() + " Y: " + input.getY());
        }
    }
}
