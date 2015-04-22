package de.tahigames.demondefense.game.world;

import java.util.List;

import de.tahigames.demondefense.engine.Entity;

/**
 * Created by Marcel on 22.04.2015.
 */
public class Level {

    private Map map;
    private static String[] mapnames = {"wasteland", "fff" , "xxx"};

    public Level(Entity root, int levelId) {
        this.map = new Map(mapnames[levelId] + ".tmx");
        root.addChild(map);
        map.addChild(new Spawner(0, 0, map.getWaves()));
    }


    public Map getMap(){
        return map;
    }

}
