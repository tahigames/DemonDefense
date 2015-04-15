package de.tahigames.demondefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

import de.tahigames.demondefense.engine.Core;
import de.tahigames.demondefense.engine.Game;

/**
 * Created by Mirco on 14.04.2015.
 */
public class DemonDefenseGame extends Game {
    private Map map;

    @Override
    public void init(Core core) {
        map = new Map(20, 20);
        core.getRoot().addChild(map);
    }

    @Override
    public void handleInput(Input input, Camera cam){
        if(Gdx.input.isTouched()){
            Vector3 screenCoord = new Vector3(input.getX(), input.getY(), 0);
            Vector3 worldCoord = cam.unproject(screenCoord);
            map.selectCellAt(worldCoord.x, worldCoord.y);
            Gdx.app.log("Input", "X: " + input.getX() + " Y: " + input.getY());
        }
    }
}
