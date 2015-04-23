package de.tahigames.demondefense.game.world;

import com.badlogic.gdx.Gdx;

import de.tahigames.demondefense.engine.core.Entity;

/**
 * Created by Marcel on 22.04.2015.
 */
public class Level {

    private Map map;
    private static String[] mapnames = {"wasteland", "fff" , "xxx"};

    public Level(Entity root, int levelId) {
        this.map = new Map(mapnames[levelId] + ".tmx");
        root.addChild(map);
        map.addChild(new Spawner(map.getStartX(), map.getStartY(), map.getWaves(), map.getPath()));
        Gdx.app.log("coord", map.getStartX() + " - " + map.getStartY());
    }


    public Map getMap(){
        return map;
    }

}
